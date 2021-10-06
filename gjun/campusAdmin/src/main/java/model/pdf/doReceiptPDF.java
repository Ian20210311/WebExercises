package model.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import dao.entity.StudentInfo;

import com.itextpdf.text.Rectangle;

public class doReceiptPDF {

	private static BaseFont baseFont = null;
	private static Font catFont = null;
	private static Font redFont = null;
	private static Font subFont = null;
	private static Font smallBold = null;
	private static Font underLineFont = null;
	private static Font waterMarkFont = null;
	private static String[] ColumnName = { "學費", "學分費", "雜費", "助學金", "小計" };

	static {
		try {
			baseFont = BaseFont.createFont("MHei-Medium", "UniCNS-UCS2-H", BaseFont.EMBEDDED);
			catFont = new Font(baseFont, 28, Font.NORMAL);
			redFont = new Font(baseFont, 16, Font.NORMAL);
			subFont = new Font(baseFont, 18, Font.NORMAL);
			smallBold = new Font(baseFont, 14, Font.NORMAL);
			underLineFont = new Font(baseFont, 18, Font.NORMAL | Font.UNDERLINE);
			waterMarkFont = new Font(baseFont, 30, Font.BOLD, BaseColor.LIGHT_GRAY);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ByteArrayOutputStream getRecipt(StudentInfo std, Image stampImage, Image AdminImg, Image clerkImg) throws Exception {

		String waterMarkstr = "僅供 <<" + std.getName() + ">> 專用";

		ByteArrayOutputStream receiptbyteStream = new ByteArrayOutputStream();

		Document document = new Document();
		Rectangle one = new Rectangle(800, 600);
		document.setPageSize(one);

		PdfWriter Writer = PdfWriter.getInstance(document, receiptbyteStream);
		document.open();

		// addMetadata
		document = addMetaData(document);

		// 新增浮水印
		PdfWatermarkUtil.configWatermark(Writer, document, waterMarkstr, waterMarkFont);
		// add title
		document = addTitlePage(document, std);

		// 新增浮水印
		PdfWatermarkUtil.configWatermark(Writer, document, waterMarkstr, waterMarkFont);

		// add content
		document = addReceiptContent(document, std);
		
		document.close();
		
		//add stamp
		ByteArrayOutputStream StampReceiptByteStream = addStamp(receiptbyteStream, stampImage, AdminImg, clerkImg);
		
		//return receiptbyteStream;
		return StampReceiptByteStream ;
	}

	private Document addMetaData(Document document) {
		document.addTitle("巨匠  Java 課程練習");
		document.addSubject("收據 Receipt");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("Freddy Ruan");
		document.addCreator("Freddy Ruan");

		return document;
	}

	// 報表封面
	private Document addTitlePage(Document document, StudentInfo std) throws DocumentException {
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);

		Paragraph titlePara = new Paragraph("閃亮亮中小學專用收款收據", catFont);
		titlePara.setAlignment(Element.ALIGN_CENTER);
		preface.add(titlePara);

		addEmptyLine(preface, 12);

		Paragraph warnPara = new Paragraph("<<本資料僅供學生[ " + std.getName() + " ]使用，不得作為銷售文宣，並禁止其他非相關人員使用或轉載>> ", subFont);
		warnPara.setAlignment(Element.ALIGN_CENTER);
		preface.add(warnPara);

		addEmptyLine(preface, 9);

		Paragraph fromPara = new Paragraph("本資料由閃亮亮中小學後台管理系統產生 " + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$
																						// //$NON-NLS-3$
				smallBold);
		fromPara.setAlignment(Element.ALIGN_CENTER);
		preface.add(fromPara);

		document.add(preface);

		// Start a new page
		document.newPage();

		return document;
	}

	// 收據內容
	public Document addReceiptContent(Document document, StudentInfo std)
			throws DocumentException, UnsupportedEncodingException, MalformedURLException, IOException {
		String addr = std.getCity() + std.getCounty() + "(" + std.getZip() + ")" + std.getRoad();

		Paragraph preface = new Paragraph();
		Paragraph addrPara = new Paragraph(addr, smallBold);
		addrPara.setAlignment(Element.ALIGN_LEFT);
		preface.add(addrPara);

		Paragraph namePara = new Paragraph(std.getName(), smallBold);
		namePara.setAlignment(Element.ALIGN_LEFT);
		preface.add(namePara);

		addEmptyLine(preface, 5);

		Paragraph titlePara = new Paragraph("收據 Receipt", catFont);
		titlePara.setAlignment(Element.ALIGN_CENTER);
		preface.add(titlePara);

		addEmptyLine(preface, 1);

		Paragraph namectrPara = new Paragraph("姓名/抬頭 : " + std.getName(), redFont);
		namectrPara.setAlignment(Element.ALIGN_JUSTIFIED);
		preface.add(namectrPara);

		addEmptyLine(preface, 1);

		// add a table
		preface = createTable(preface);

		Paragraph TotalPara = new Paragraph("總金額 : NT" + "7023", smallBold);
		TotalPara.setAlignment(Element.ALIGN_RIGHT);
		preface.add(TotalPara);

		addEmptyLine(preface, 1);

		addEmptyLine(preface, 2);

		Paragraph agencyPara = new Paragraph("機構章 : _______________  主管 : _______________  經辦人 : _______________ ",
				smallBold);
		agencyPara.setAlignment(Element.ALIGN_CENTER);
		preface.add(agencyPara);

		addEmptyLine(preface, 1);

		// add a table
		preface = createMemoTable(preface);

		// now add all this to the document
		document.add(preface);

		document.newPage();

		return document;

	}

	private Paragraph createTable(Paragraph preface)
			throws BadElementException, UnsupportedEncodingException, DocumentException {
		// 設定Row numbers

		PdfPTable table = new PdfPTable(ColumnName.length);

		// changing the width percentage
		table.setWidthPercentage(100);

		PdfPCell tblTitle = null;
		for (int i = 0; i < ColumnName.length; i++) {
			// byte[] ptext = ColumnNames[i].getBytes("ISO-8859-1");
			// String value = new String(ptext, "UTF-8");
			// String value = new String(ptext, baseFont.getEncoding());
			// tblTitle = new PdfPCell(new Phrase(value, subFont));
			tblTitle = new PdfPCell(new Phrase(ColumnName[i], smallBold));
			tblTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(tblTitle);
		}

		table.setHeaderRows(1);

		PdfPCell tblRow = null;

		// 學費"
		tblRow = new PdfPCell(new Phrase("4812", subFont));
		tblRow.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(tblRow);

		// 學分費
		tblRow = new PdfPCell(new Phrase("1235", subFont));
		tblRow.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(tblRow);

		// 雜費
		tblRow = new PdfPCell(new Phrase("2356", subFont));
		tblRow.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(tblRow);

		// 助學金
		tblRow = new PdfPCell(new Phrase("1380", subFont));
		tblRow.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(tblRow);

		// 小計
		tblRow = new PdfPCell(new Phrase("7023", subFont));
		tblRow.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(tblRow);

		preface.add(table);

		return preface;

	}

	private Paragraph createMemoTable(Paragraph preface)
			throws BadElementException, UnsupportedEncodingException, DocumentException {
		// 設定Row numbers

		float[] columnWidths = { 1, 10, 1, 10 };
		PdfPTable table = new PdfPTable(columnWidths);

		// changing the width percentage
		table.setWidthPercentage(100);

		PdfPCell tblTitle = null;

		// 設定Table
		tblTitle = new PdfPCell(new Phrase("組\n織\n訊\n息", subFont));
		tblTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(tblTitle);
		
		String strPhraseOrg = "\n\n閃亮亮\nEC平台\n\n";
		tblTitle = new PdfPCell(new Phrase(strPhraseOrg, subFont));
		tblTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(tblTitle);

		tblTitle = new PdfPCell(new Phrase("組\n織\n訊\n息", subFont));
		tblTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(tblTitle);

		//String strPhrase = "\n閃亮亮EC平台\n台北市中正區忠孝東路一段100號\n聯絡我們 : service@gjun.com.tw\n\n";
		String strPhrase = "\n地址 : 台北市中正區忠孝東路一段100號\n聯絡我們 : service@gjun.com.tw\n電話 : (02)1234-5678\n\n";
		tblTitle = new PdfPCell(new Phrase(strPhrase, subFont));
		tblTitle.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(tblTitle);


		preface.add(table);

		return preface;

	}

	//新增印章
	private ByteArrayOutputStream addStamp(ByteArrayOutputStream receiptbyteStream
											, Image stampImage
											, Image AdminImg
											, Image clerkImg)
	{
		ByteArrayOutputStream StampReceiptByteStream = new ByteArrayOutputStream();
		try 
		{
			
			
			
			ByteArrayInputStream byteIS = new ByteArrayInputStream(receiptbyteStream.toByteArray());

			InputStream is = (InputStream) byteIS;

			// Read the existing PDF document
			PdfReader pdfReader = new PdfReader(is);

			// Get the PdfStamper object
			PdfStamper pdfStamper = new PdfStamper(pdfReader, StampReceiptByteStream);

			PdfContentByte waterMarkPdfContent = pdfStamper.getOverContent(pdfReader.getNumberOfPages());
			//PdfContentByte waterMarkPdfContent = pdfStamper.getOverContent(1);
			
			// 公司章印章位置
			stampImage.setAbsolutePosition(230, 200);
			// 公司章印章大小
			stampImage.scalePercent(50);
			
			//主管章印章位置
			AdminImg.setAbsolutePosition(380, 200);
			//主管章印章大小
			AdminImg.scalePercent(20);
			
			//經辦章印章位置
			clerkImg.setAbsolutePosition(560, 200);
			//經辦章印章大小
			clerkImg.scalePercent(20);

			waterMarkPdfContent.addImage(stampImage);
			waterMarkPdfContent.addImage(AdminImg);
			waterMarkPdfContent.addImage(clerkImg);
			
			pdfStamper.close();
			pdfReader.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			return StampReceiptByteStream ;
		}
	}

	// new line
	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
