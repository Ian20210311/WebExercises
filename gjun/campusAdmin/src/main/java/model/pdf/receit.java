package model.pdf;

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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.chart.Jcharts;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException ;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class receit {
	
	private static BaseFont baseFont = null ;
    private static Font catFont = null ;
    private static Font redFont = null ;
    private static Font subFont = null ;
    private static Font smallBold = null ;

	public receit()
	{
		try
		{
			baseFont = BaseFont.createFont("MHei-Medium", "UniCNS-UCS2-H", BaseFont.EMBEDDED );
            catFont = new Font(baseFont, 28, Font.NORMAL);
            redFont = new Font(baseFont, 16, Font.NORMAL);
            subFont = new Font(baseFont, 12, Font.NORMAL);
            smallBold = new Font(baseFont, 14, Font.NORMAL);
			
			String Fpath = "pdf_file/receiptPdf.pdf";
			Document d = new Document();
			PdfWriter writer = PdfWriter.getInstance(d , new FileOutputStream(Fpath));
			d.open();
			d.add(new Paragraph("Hello"));
			
			//Paragraph p = new Paragraph()
			
			d.add(new Paragraph("你好這是中文", catFont));
			
			PdfPTable tbl = new PdfPTable(5);
			PdfPCell tblTitle = null ;
			for(int i=0 ; i<5 ; i++)
	        {
	            //byte[] ptext = ColumnNames[i].getBytes("ISO-8859-1");  
	            //String value = new String(ptext, "UTF-8");
	            //String value = new String(ptext, baseFont.getEncoding());
	            //tblTitle = new PdfPCell(new Phrase(value, subFont));
	            tblTitle = new PdfPCell(new Phrase("Hello", subFont));
	            tblTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
	            tbl.addCell(tblTitle);
	        }
	        
			tbl.setHeaderRows(1);
			
			PdfPCell tblRow = null ;
			
	        for(int i=0 ; i<5 ; i++)
	        {
	            for(int j=0 ; j<10; j++)
	            {
	                tblRow = new PdfPCell(new Phrase("Hello", subFont));
	                tblRow.setHorizontalAlignment(Element.ALIGN_CENTER);
	                tbl.addCell(tblRow);
	                //table.addCell(b);
	            }
	        }
			
			d.add(tbl);
			
			
			
			d.close();
			
			File pdfFile = new File(Fpath);
            Desktop desktop = Desktop.getDesktop();
            if(pdfFile.exists()) desktop.open(pdfFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
