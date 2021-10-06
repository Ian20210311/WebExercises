package controller.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.Document;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfReader;

import controller.util.allutil;
import dao.entity.StudentInfo;
import model.pdf.doReceiptPDF;

/**
 * Servlet implementation class requestReceiptServlet
 */
@WebServlet("/requestReceiptServlet")
public class requestReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public requestReceiptServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ByteArrayOutputStream receiptbyteStream = new ByteArrayOutputStream();

		Properties p = new Properties();
		p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("campusAdmin.properties"));
		String lineRestURL = p.getProperty("lineRest.URL");
		
		
		try {
			/*
			 * // 取的學生資料 String jsonStd = new allutil().readJson(lineRestURL +
			 * "/fetchStudent/A123456789");
			 * 
			 * GsonBuilder builder = new GsonBuilder(); builder.setPrettyPrinting(); Gson
			 * gson = builder.create();
			 * 
			 * StudentInfo std = null; std = gson.fromJson(jsonStd, StudentInfo.class);
			 */

			StudentInfo std = (StudentInfo) request.getSession().getAttribute("studentInfo");

			if (std != null) {

				// 取得圖檔
				String imgFile = p.getProperty("pdf.receipt.stampimg");
				String adminImgFile = p.getProperty("pdf.receipt.Adminimg");
				String clerkImgFile = p.getProperty("pdf.receipt.clerkimg");
				
				//機構章
				byte[] imgBytes = new allutil().readlocalJSON(getServletContext().getResourceAsStream(imgFile));
				Image stampImage = Image.getInstance(imgBytes);
				
				//主管章
				byte[] adminImgBytes = new allutil().readlocalJSON(getServletContext().getResourceAsStream(adminImgFile));
				Image AdminImg = Image.getInstance(adminImgBytes);
				
				//經辦章
				byte[] clerkImgBytes = new allutil().readlocalJSON(getServletContext().getResourceAsStream(clerkImgFile));
				Image clerkImg = Image.getInstance(clerkImgBytes);
				
				
				
				receiptbyteStream = new doReceiptPDF().getRecipt(std, stampImage, AdminImg, clerkImg);
				
				//save temp pdf
				//String tmpReceiptPDFfile = p.getProperty("pda.receipt.filepath") + p.getProperty("pda.receipt.filename");
				
				
				
				//String pdfName = "receipt.pdf" ;
				//File file = new File(tmpReceiptPDFfile);
				//OutputStream outputStream = new FileOutputStream (file);
				//receiptbyteStream.writeTo(outputStream);
				
				
				
				//取得 PdfReader 
				//PdfReader pdfReader = new PdfReader(getServletContext().getRealPath(tmpReceiptpdfPath)+ pdfName);
				
				//genReceipt.addStamp(pdfReader, stampImage, tmpReceiptPDFfile);


				
				
				

				// 設定response 為PDF
				response.setContentType("application/pdf");
				// response.setHeader("Content-disposition", "attachment;
				// filename=receiptPdf.pdf");

				//
				response.setContentLength(receiptbyteStream.size());
				ServletOutputStream receipt_out = response.getOutputStream();
				receiptbyteStream.writeTo(receipt_out);
				receipt_out.flush();
				receipt_out.close();
			} else {
				request.setAttribute("RtnErrMsg", "申請繳費收據失敗, 請重新作業!!");
				request.getRequestDispatcher("err.jsp").forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute("RtnErrMsg", "新增receiptPDF失敗，請確認 doReceiptPDF.java");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		} finally {
			// response.getWriter().append("Served at: ").append(request.getContextPath());
		}

	}

}
