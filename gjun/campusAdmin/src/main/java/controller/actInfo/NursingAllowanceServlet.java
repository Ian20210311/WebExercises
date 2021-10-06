package controller.actInfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.util.allutil;
import model.jsoninfo.Nursing_allowance_info;

/**
 * Servlet implementation class NursingAllowanceServlet
 */
@WebServlet("/NursingAllowanceServlet")
public class NursingAllowanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NursingAllowanceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filePath = "/WEB-INF/json/Nursing_allowance.json";
		// readlocalfile
		byte[] jsonByte = new allutil().readlocalJSON(getServletContext().getResourceAsStream(filePath));
		// read JSON file
		ObjectMapper objectMapper = new ObjectMapper();
		Nursing_allowance_info[] na = null;
		na = objectMapper.readValue(jsonByte, Nursing_allowance_info[].class);

		request.setAttribute("na", na);
		request.getRequestDispatcher("WEB-INF/jsp/Nursing_allowance.jsp").forward(request, response);
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
