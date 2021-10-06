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

import model.jsoninfo.pigDetail;

/**
 * Servlet implementation class pigServlet
 */
@WebServlet("/pigServlet")
public class pigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public pigServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 臺灣豬證明標章商家資料
		String filePath = "/WEB-INF/json/TaiwanPig.json";
		//String filePath = "/WEB-INF/json/xxx.json";
		
		// readlocalfile
		byte[] jsonByte = new allutil().readlocalJSON(getServletContext().getResourceAsStream(filePath));
		// read JSON file
		ObjectMapper objectMapper = new ObjectMapper();
		pigDetail[] pigsList = null;
		// 全局DeserializationFeature配置
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		pigsList = objectMapper.readValue(jsonByte, pigDetail[].class);
		// get shows info from musicShows[]
		String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pigsList);

		//System.out.println(jsonString);
		
		request.getSession().setAttribute("pigsList", pigsList) ;
		request.getRequestDispatcher("WEB-INF/jsp/goodPigs.jsp").forward(request, response);
		
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
