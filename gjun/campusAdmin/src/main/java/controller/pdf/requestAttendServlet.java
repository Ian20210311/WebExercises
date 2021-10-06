package controller.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

import controller.util.allutil;
import dao.entity.StudentInfo;
import model.pdf.doAttendPDF;

/**
 * Servlet implementation class requestAttendServlet
 */
@WebServlet("/requestAttendServlet")
public class requestAttendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public requestAttendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream attendByteStream = new ByteArrayOutputStream();

		Properties p = new Properties();
		p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("campusAdmin.properties"));
		String toSchoolTime = p.getProperty("school.time.toschool");
		//String freehartPath = request.getServletContext().getRealPath(p.getProperty("pdf.freehart.path"));
		try
		{
			StudentInfo std = (StudentInfo) request.getSession().getAttribute("studentInfo");
			if(std != null)
			{
				
				// 取得圖檔
				String imgFile = p.getProperty("pdf.receipt.warning");
				
				//機構章
				byte[] imgBytes = new allutil().readlocalJSON(getServletContext().getResourceAsStream(imgFile));
				Image stampImage = Image.getInstance(imgBytes);
				
				
				attendByteStream = new doAttendPDF().getAttend(std, toSchoolTime, stampImage);
				
				response.setContentType("application/pdf");

				response.setContentLength(attendByteStream.size());
				ServletOutputStream receipt_out = response.getOutputStream();
				attendByteStream.writeTo(receipt_out);
				receipt_out.flush();
				receipt_out.close();
			}
			else
			{
				request.setAttribute("RtnErrMsg", "查詢出席紀錄報告失敗, 請重新作業!!");
				request.getRequestDispatcher("err.jsp").forward(request, response);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			request.setAttribute("RtnErrMsg", "新增attendPDF失敗，請確認 doAttendPDF.java");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
		finally
		{
			
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
