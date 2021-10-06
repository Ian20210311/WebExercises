package controller.actInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.util.allutil;
import model.jsoninfo.course;

/**
 * Servlet implementation class hakaServlet
 */
@WebServlet("/hakaServlet")
public class hakaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public hakaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * use gson //客家委員會好客小學堂(https://data.gov.tw/dataset/129035) String filePath =
		 * "https://cloud.hakka.gov.tw/Pub/Opendata/DTST20200800001.json";
		 * 
		 * //String filePath = request.getParameter("addr");
		 * 
		 * //another String jsonStr = new allutil().readJson(filePath);
		 * 
		 * GsonBuilder builder = new GsonBuilder(); 
		 * builder.setPrettyPrinting(); 
		 * Gson gson = builder.create();
		 * 
		 * course[] allCourse = null;
		 * 
		 * allCourse = gson.fromJson(jsonStr, course[].class);
		 */

		String filePath = "/WEB-INF/json/hakacourse.json";
		// readlocalfile
		byte[] jsonByte = new allutil().readlocalJSON(getServletContext().getResourceAsStream(filePath));
		// read JSON file
		ObjectMapper objectMapper = new ObjectMapper();
		course[] allCourse = null;
		allCourse = objectMapper.readValue(jsonByte, course[].class);
		// get city info from pojoJson[]
		String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allCourse);

		request.setAttribute("allcourse", allCourse);
		request.getRequestDispatcher("WEB-INF/jsp/hakacourse.jsp").forward(request, response);

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
