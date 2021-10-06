package model.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfWatermarkUtil {

	public static void configWatermark(PdfWriter writer, Document document, String waterMarkText, Font waterMarkFont) {
		try {
			float pageWidth = document.right() + document.left();// 獲取pdf內容正文頁面寬度
			float pageHeight = document.top() + document.bottom();// 獲取pdf內容正文頁面高度
			// 設置水印字體格式
			// BaseFont base = BaseFont.createFont("STSong-Light",
			// "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			// Font waterMarkFont = new Font(base, 20, Font.BOLD, BaseColor.LIGHT_GRAY);
			PdfContentByte waterMarkPdfContent = writer.getDirectContentUnder();
			Phrase phrase = new Phrase(waterMarkText, waterMarkFont);
			// 兩行三列
			ColumnText.showTextAligned(waterMarkPdfContent, Element.ALIGN_CENTER, phrase, pageWidth * 0.25f,
					pageHeight * 0.2f, 45);
			ColumnText.showTextAligned(waterMarkPdfContent, Element.ALIGN_CENTER, phrase, pageWidth * 0.25f,
					pageHeight * 0.5f, 45);
			ColumnText.showTextAligned(waterMarkPdfContent, Element.ALIGN_CENTER, phrase, pageWidth * 0.25f,
					pageHeight * 0.8f, 45);
			ColumnText.showTextAligned(waterMarkPdfContent, Element.ALIGN_CENTER, phrase, pageWidth * 0.65f,
					pageHeight * 0.2f, 45);
			ColumnText.showTextAligned(waterMarkPdfContent, Element.ALIGN_CENTER, phrase, pageWidth * 0.65f,
					pageHeight * 0.5f, 45);
			ColumnText.showTextAligned(waterMarkPdfContent, Element.ALIGN_CENTER, phrase, pageWidth * 0.65f,
					pageHeight * 0.8f, 45);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ByteArrayOutputStream addITextWatermark(ByteArrayOutputStream attendbyteStream, Image stampImage)
			throws Exception {

		ByteArrayOutputStream waterMarkByteStream = new ByteArrayOutputStream();

		ByteArrayInputStream byteIS = new ByteArrayInputStream(attendbyteStream.toByteArray());

		InputStream is = (InputStream) byteIS;

		// Read the existing PDF document
		PdfReader pdfReader = new PdfReader(is);

		// Get the PdfStamper object
		PdfStamper pdfStamper = new PdfStamper(pdfReader, waterMarkByteStream);

		// PdfContentByte waterMarkPdfContent =
		// pdfStamper.getOverContent(pdfReader.getNumberOfPages());
		PdfContentByte waterMarkPdfContent = pdfStamper.getOverContent(2);

		// 公司章印章位置
		stampImage.setAbsolutePosition(230, 330);
		//stampImage.setAbsolutePosition(32, 200);
		// 公司章印章大小
		stampImage.scalePercent(43);
		//stampImage.scalePercent(60);
		
		waterMarkPdfContent.addImage(stampImage);

		pdfStamper.close();
		pdfReader.close();

		return waterMarkByteStream;

		// Get the PdfStamper object
		// PdfStamper pdfStamper = new PdfStamper(pdfReader, receiptbyteStream);

		// Get the PdfContentByte type by pdfStamper.
		// for (int i = 1, pdfPageSize = pdfReader.getNumberOfPages() + 1; i <
		// pdfPageSize; i++) {
		// PdfContentByte pageContent = pdfStamper.getOverContent(i);
		// pageContent.setGState(this.getPdfGState());
		// pageContent.beginText();
		// pageContent.setFontAndSize(this.getBaseFont(), 20);
		// pageContent.setColorFill(BaseColor.LIGHT_GRAY);
		// pageContent.showTextAligned(Element.ALIGN_CENTER, waterMarkText,
		// document.getPageSize().getWidth() / 2,
		// document.getPageSize().getHeight() / 2, 0);
		// pageContent.endText();
		// }
		// pdfStamper.close();
	}

	/**
	 * Get BaseFont
	 *
	 * @return
	 * @throws Exception
	 */
	private BaseFont getBaseFont() throws Exception {
		return BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
	}

	/**
	 * Get PdfGState
	 *
	 * @return
	 */
	private PdfGState getPdfGState() {
		PdfGState graphicState = new PdfGState();
		graphicState.setFillOpacity(0.7f);
		graphicState.setStrokeOpacity(1f);

		return graphicState;
	}

}
