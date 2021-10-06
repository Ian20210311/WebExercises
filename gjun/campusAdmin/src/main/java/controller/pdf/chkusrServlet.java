package controller.pdf;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.util.allutil;
import dao.entity.StudentInfo;

/**
 * Servlet implementation class chkusrServlet
 */
@WebServlet("/chkusrServlet")
public class chkusrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public chkusrServlet() {
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
		
		String[] stdID = (request.getParameter("data")).split(":");
		
		Properties p = new Properties();
		p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("campusAdmin.properties"));
		String lineRestURL = p.getProperty("lineRest.URL");
		
		System.out.println(lineRestURL + "/chkAndfetchStudent/data/" + request.getParameter("data").trim());

		StudentInfo std = null;
		try {
			String jsonStd = new allutil()
					.readJson(lineRestURL + "/chkAndfetchStudent/data/" + request.getParameter("data").trim());
			System.out.println(jsonStd);

			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();

			std = gson.fromJson(jsonStd, StudentInfo.class);

		} catch (Exception e) {
			System.out.println(e.toString());

			request.setAttribute("RtnErrMsg", "檢查user失敗，請確認 chkusrServlet.java");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		} finally {
			if (std != null) {
				request.getSession().setAttribute("studentInfo", std);
				request.getRequestDispatcher("jsp/pdf/pdfTable.jsp").forward(request, response);
			} else {
				System.out.println("The regrtn = SSSSSSSSSSSSSSSSSSSSSSSQ") ;
				//request.setAttribute("RtnErrMsg", "您帳號密碼有誤!!");
				//request.getRequestDispatcher("err.jsp").forward(request, response);
				request.getRequestDispatcher("jsp/pdf/pdfTable.jsp").forward(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
