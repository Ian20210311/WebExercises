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
import model.jsoninfo.AfterSchoolData;

/**
 * Servlet implementation class afterschoolservlet
 */
@WebServlet("/afterschoolservlet")
public class afterschoolservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public afterschoolservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 新北市立案短期補習班基本資料
		String filePath = "/WEB-INF/json/aaa.json";
		//String filePath = "/WEB-INF/json/xxx.json";
		
		// readlocalfile
		byte[] jsonByte = new allutil().readlocalJSON(getServletContext().getResourceAsStream(filePath));
		// read JSON file
		ObjectMapper objectMapper = new ObjectMapper();
		AfterSchoolData[] afterschoolList = null;
		// 全局DeserializationFeature配置
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		afterschoolList = objectMapper.readValue(jsonByte, AfterSchoolData[].class);
		// get shows info from musicShows[]
		String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(afterschoolList);

		//System.out.println(jsonString);
		
		request.getSession().setAttribute("afterschoolList", afterschoolList) ;
		request.getRequestDispatcher("WEB-INF/jsp/afterschool.jsp").forward(request, response);
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
