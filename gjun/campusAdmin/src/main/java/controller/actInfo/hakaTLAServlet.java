package controller.actInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.util.allutil;
import model.jsoninfo.hakaInformatioCourse;
import model.jsoninfo.hakaTLA;
import model.jsoninfo.hakaa;

/**
 * Servlet implementation class hakaTLAServlet
 */
@WebServlet("/hakaTLAServlet")
public class hakaTLAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hakaTLAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = "/WEB-INF/json/hakaTLA.json";
		// readlocalfile
		byte[] jsonByte = new allutil().readlocalJSON(getServletContext().getResourceAsStream(filePath));
		// read JSON file
		ObjectMapper objectMapper = new ObjectMapper();
		hakaTLA[] hakat = null;
		hakat = objectMapper.readValue(jsonByte, hakaTLA[].class);
		System.out.println(hakat);
		// get city info from pojoJson[]
		String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(hakat);

		request.setAttribute("hakat", hakat);
		request.getRequestDispatcher("WEB-INF/jsp/hakaTLA_1.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
