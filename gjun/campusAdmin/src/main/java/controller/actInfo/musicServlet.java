package controller.actInfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.util.allutil;
import model.jsoninfo.musicShows;

/**
 * Servlet implementation class musicServlet
 */
@WebServlet("/musicServlet")
public class musicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public musicServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//音樂表演資訊(文化部整合本部及所屬各級機關(構)、以及其他公、民營單位之音樂劇場表演訊息)
		String filePath = "/WEB-INF/json/music.json";
		
		// readlocalfile
		byte[] jsonByte = new allutil().readlocalJSON(getServletContext().getResourceAsStream(filePath));
		// read JSON file
		ObjectMapper objectMapper = new ObjectMapper();
		musicShows[] allShows = null;
		//全局DeserializationFeature配置
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		allShows = objectMapper.readValue(jsonByte, musicShows[].class);
		// get shows info from musicShows[]
		String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allShows);

		//System.out.println(jsonString);
		
		request.getSession().setAttribute("allShow", allShows) ;
		request.getRequestDispatcher("WEB-INF/jsp/musicShow.jsp").forward(request, response);
		

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
