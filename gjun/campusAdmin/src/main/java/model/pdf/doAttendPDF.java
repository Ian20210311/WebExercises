package model.pdf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Section;

import dao.entity.DailyChk;
import dao.entity.StudentInfo;
import model.chart.Jcharts;

public class doAttendPDF {
	private static BaseFont baseFont = null;
	private static Font catFont = null;
	private static Font redFont = null;
	private static Font subFont = null;
	private static Font smallBold = null;
	private static Font normalFont = null;
	private static Font blueFont = null;
	private static Font underLineFont = null;
	private static Font waterMarkFont = null;
	private static String[] ColumnName = { "學費", "學分費", "雜費", "助學金", "小計" };

	// 界定遲到時間
	private String toSchoolTime = null;
	// 計算結果紀錄
	private Map<String, Integer> toSchoolNumMap = null;
	private PdfWriter Writer = null;
	

	static {
		try {
			baseFont = BaseFont.createFont("MHei-Medium", "UniCNS-UCS2-H", BaseFont.EMBEDDED);
			catFont = new Font(baseFont, 28, Font.NORMAL);
			subFont = new Font(baseFont, 16, Font.NORMAL);
			redFont = new Font(baseFont, 16, Font.NORMAL, BaseColor.RED);
			blueFont = new Font(baseFont, 14, Font.NORMAL, BaseColor.BLUE);
			normalFont = new Font(baseFont, 14, Font.BOLD);
			smallBold = new Font(baseFont, 12, Font.BOLD);
			underLineFont = new Font(baseFont, 14, Font.NORMAL | Font.UNDERLINE);
			waterMarkFont = new Font(baseFont, 30, Font.BOLD, BaseColor.LIGHT_GRAY);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public doAttendPDF() {
		super();
		// TODO Auto-generated constructor stub
		this.toSchoolNumMap =  new HashMap<String, Integer>();

		// init toSchoolNumMap
		this.toSchoolNumMap.put("Late", 0);
		this.toSchoolNumMap.put("onTime", 0);
	}

	public String getToSchoolTime() {
		return toSchoolTime;
	}

	public void setToSchoolTime(String toSchoolTime) {
		this.toSchoolTime = toSchoolTime;
	}

	public Map<String, Integer> getToSchoolNumMap() {
		return toSchoolNumMap;
	}

	public void setToSchoolNumMap(Map<String, Integer> toSchoolNumMap) {
		this.toSchoolNumMap = toSchoolNumMap;
	}

	public PdfWriter getWriter() {
		return Writer;
	}

	public void setWriter(PdfWriter writer) {
		Writer = writer;
	}
	
	// 取得pdf
	public ByteArrayOutputStream getAttend(StudentInfo std, String toSchoolTime, Image stampImage) throws Exception 
	{
		// 設定到校時間
		this.setToSchoolTime(toSchoolTime);

		String waterMarkstr = "僅供 <<" + std.getName() + ">> 專用";

		ByteArrayOutputStream attendbyteStream = new ByteArrayOutputStream();

		Document document = new Document();
		Rectangle one = new Rectangle(800, 600);
		document.setPageSize(one);

		this.setWriter(PdfWriter.getInstance(document, attendbyteStream));
		document.open();

		// addMetadata
		document = addMetaData(document);

		// 新增浮水印
		PdfWatermarkUtil.configWatermark(Writer, document, waterMarkstr, waterMarkFont);
		// add title
		document = addTitlePage(document, std);
		
		// 新增內容
		document = addContent(document, std);
		// 新增浮水印
		PdfWatermarkUtil.configWatermark(Writer, document, waterMarkstr, waterMarkFont);
		
		
		
        
		document.close();
		
		
		
		//add waterMark
		ByteArrayOutputStream waterMarkByteStream = PdfWatermarkUtil.addITextWatermark(attendbyteStream,
														stampImage);
				
		//return waterMarkByteStream;
		return waterMarkByteStream ;
		
		//return attendbyteStream;
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

		Paragraph titlePara = new Paragraph("閃亮亮中小學出勤紀錄統計表", catFont);
		titlePara.setAlignment(Element.ALIGN_CENTER);
		preface.add(titlePara);

		addEmptyLine(preface, 12);

		Paragraph warnPara = new Paragraph("<<本資料僅供學生[ " + std.getName() + " ]使用，不得作為銷售文宣，並禁止其他非相關人員使用或轉載>> ", subFont);
		warnPara.setAlignment(Element.ALIGN_CENTER);
		preface.add(warnPara);

		addEmptyLine(preface, 9);

		Paragraph fromPara = new Paragraph("本資料由閃亮亮中小學後台管理系統產生 " + ", " + new Date(), underLineFont);
		fromPara.setAlignment(Element.ALIGN_CENTER);
		preface.add(fromPara);

		document.add(preface);

		// Start a new page
		// document.newPage();
		
		return document;
	}

	// 新增內容
	private Document addContent(Document document, StudentInfo std)
			throws DocumentException, UnsupportedEncodingException, MalformedURLException, IOException {

		// *******************************************************************
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);
		
		// 第一章
		Anchor anchor_stdinfo = new Anchor("學生相關基本資料", catFont);
		anchor_stdinfo.setName("相關訊息");
		// 第一章(詳細資料)
		Chapter chapter1 = new Chapter(new Paragraph(new Paragraph(anchor_stdinfo)), 1);

		// session 1
		Paragraph section1_Para = new Paragraph("詳細資料", subFont);
		Section section1 = chapter1.addSection(section1_Para, 2);

		addEmptyLine(section1_Para, 1);

		// 新增第一節 student info all Paragraph
		Paragraph stdInfo_preface = new Paragraph();

		// 編號
		Paragraph numPara = new Paragraph("<<編號>> : " + std.getStudentNo(), blueFont);
		numPara.setAlignment(Element.ALIGN_LEFT);
		stdInfo_preface.add(numPara);

		// 姓名
		Paragraph namePara = new Paragraph("<<姓名>> : " + std.getName(), blueFont);
		namePara.setAlignment(Element.ALIGN_LEFT);
		stdInfo_preface.add(namePara);

		// 身分證號
		Paragraph idPara = new Paragraph("<<身分證號>> : " + std.getStudentId(), blueFont);
		idPara.setAlignment(Element.ALIGN_LEFT);
		stdInfo_preface.add(idPara);

		// 縣市
		Paragraph cityPara = new Paragraph("<<縣市>> : " + std.getCity(), blueFont);
		cityPara.setAlignment(Element.ALIGN_LEFT);
		stdInfo_preface.add(cityPara);

		// 鄉鎮市區
		Paragraph countyPara = new Paragraph("<<鄉鎮市區>> : " + std.getCounty(), blueFont);
		countyPara.setAlignment(Element.ALIGN_LEFT);
		stdInfo_preface.add(countyPara);

		// 郵遞區號
		Paragraph zipPara = new Paragraph("<<郵遞區號>> : " + std.getZip(), blueFont);
		zipPara.setAlignment(Element.ALIGN_LEFT);
		stdInfo_preface.add(zipPara);

		// 地址
		Paragraph roadPara = new Paragraph("<<地址>> : " + std.getRoad(), blueFont);
		roadPara.setAlignment(Element.ALIGN_LEFT);
		stdInfo_preface.add(roadPara);

		// email
		Paragraph emailPara = new Paragraph("<<Email>> : " + std.getParentMail(), blueFont);
		emailPara.setAlignment(Element.ALIGN_LEFT);
		stdInfo_preface.add(emailPara);

		addEmptyLine(stdInfo_preface, 1);

		// 第一節新增 Paragraph
		section1.add(stdInfo_preface);

		// session 2
		Paragraph subPara_toSchool = new Paragraph(" " + std.getName() + " 出缺勤一覽表", subFont);
		Section section2 = chapter1.addSection(subPara_toSchool);

		addEmptyLine(subPara_toSchool, 1);

		// add a table
		section2 = createTable(std, section2);

		// document 新增 第一節
		document.add(chapter1);
		// *******************************************************************

		// 第二章
		Anchor anchor_piechart = new Anchor("相關圖表", catFont);
		anchor_piechart.setName("相關圖表");

		// 第一節(圓餅圖)
		Chapter chapter_piechart = new Chapter(new Paragraph(new Paragraph(anchor_piechart)), 1);

		// session 1
		Paragraph sec_Para_PieChart = new Paragraph("出勤圓餅圖", subFont);
		Section section_PieChart = chapter_piechart.addSection(sec_Para_PieChart, 2);

		addEmptyLine(sec_Para_PieChart, 1);

		// 新增第二節 student info all Paragraph
		Paragraph piechart_preface = new Paragraph();

		Jcharts makePie = new Jcharts(this.getToSchoolNumMap());
		// 產生圓餅圖
		BufferedImage bufferedImage = makePie.doPieChart();

		Image pieImage = Image.getInstance(this.getWriter(), bufferedImage, 1.0f);
		pieImage.setAlignment(Image.ALIGN_CENTER);

		piechart_preface.add(pieImage);
		section_PieChart.add(piechart_preface);
		document.add(chapter_piechart);

		return document;

	}

	private Section createTable(StudentInfo std, Section section)
			throws BadElementException, UnsupportedEncodingException {
		// 設定Row numbers

		String[] ColumnName = { "流水號", "到校日期", "到校時間", "是否遲到" };
		PdfPTable table = new PdfPTable(ColumnName.length);

		List<DailyChk> DailyChksLlsts = std.getDailyChks();

		if (DailyChksLlsts.size() > 0) {
			// changing the width percentage
			table.setWidthPercentage(85);

			PdfPCell tblTitle = null;
			for (int i = 0; i < ColumnName.length; i++) {
				tblTitle = new PdfPCell(new Phrase(ColumnName[i], subFont));
				tblTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(tblTitle);
			}

			table.setHeaderRows(1);

			// 寫table
			PdfPCell tblColm = null;
			String toSchoolDate = "";
			String toSchoolTime = "";
			for (DailyChk d : DailyChksLlsts) {
				// 到校時間
				Timestamp t = d.getChkInTime();

				// 流水號
				tblColm = new PdfPCell(new Phrase(String.valueOf(d.getDailyNo()), subFont));
				tblColm.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(tblColm);

				// 到校日期
				toSchoolDate = t.toLocalDateTime().getYear() + "-" + t.toLocalDateTime().getMonthValue() + "-"
						+ t.toLocalDateTime().getDayOfMonth();
				tblColm = new PdfPCell(new Phrase(toSchoolDate, subFont));
				tblColm.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(tblColm);

				// 到校時間
				toSchoolTime = t.toLocalDateTime().getHour() + ":" + t.toLocalDateTime().getMinute() + ":"
						+ t.toLocalDateTime().getSecond();
				tblColm = new PdfPCell(new Phrase(toSchoolTime, subFont));
				tblColm.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(tblColm);

				// 是否遲到
				tblColm = new PdfPCell(new Phrase(chktoSchool(t, toSchoolDate), redFont));
				tblColm.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(tblColm);
			}

			section.add(table);
		}
		return section;
	}

	// check late or not
	private String chktoSchool(Timestamp t, String toSchoolDate) {
		String rtnchk = "";

		Map<String, Integer> calcSchoolNumMap = this.getToSchoolNumMap();

		// init toSchoolNumMap
		//calcSchoolNumMap.put("Late", 0);
		//calcSchoolNumMap.put("onTime", 0);

		// 比對時間
		Timestamp StandardToSchoolTimeStamp = Timestamp.valueOf(toSchoolDate + " " + this.getToSchoolTime());
		if (t.after(StandardToSchoolTimeStamp)) {
			rtnchk = "遲到";
			calcSchoolNumMap.put("Late", calcSchoolNumMap.get("Late") + 1);
		} else {
			rtnchk = "準時";
			calcSchoolNumMap.put("onTime", calcSchoolNumMap.get("onTime") + 1);
		}

		this.setToSchoolNumMap(calcSchoolNumMap);
		System.out.println("Late num = " + calcSchoolNumMap.get("Late"));
		System.out.println("onTime num = " + calcSchoolNumMap.get("onTime"));
		return rtnchk;
	}

	// new line
	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
