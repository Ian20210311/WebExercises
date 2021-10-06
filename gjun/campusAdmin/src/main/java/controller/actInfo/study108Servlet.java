package controller.actInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.util.allutil;
import model.jsoninfo.study108;

/**
 * Servlet implementation class study108msg
 */
@WebServlet("/study108Servlet")
public class study108Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public study108Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String jsonURL="https://cloud.hakka.gov.tw/Pub/Opendata/DTST20200300024.json";
		String filePath = "/WEB-INF/json/study108.json";
		String jspsit = "WEB-INF/jsp/study108.jsp";
		// readlocalfile
		byte[] jsonByte = new allutil().readlocalJSON(getServletContext().getResourceAsStream(filePath));
		// read JSON file
		ObjectMapper objectMapper = new ObjectMapper();
		study108[] data = null;
		data = objectMapper.readValue(jsonByte, study108[].class);
		// get city info from pojoJson[]
		String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		request.setAttribute("data", data);
		request.getRequestDispatcher("WEB-INF/jsp/study108.jsp").forward(request, response);
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
