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
import model.jsoninfo.Water;
import model.jsoninfo.allWater;
import model.jsoninfo.course;
import model.jsoninfo.pigDetail;

/**
 * Servlet implementation class waterServlet
 */
@WebServlet("/waterServlet")
public class waterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public waterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 臺灣豬證明標章商家資料
				String filePath = "/WEB-INF/json/values.json";
				//String filePath = "/WEB-INF/json/xxx.json";
				
				// readlocalfile
				byte[] jsonByte = new allutil().readlocalJSON(getServletContext().getResourceAsStream(filePath));
				// read JSON file
				ObjectMapper objectMapper = new ObjectMapper();
				Water[] WaterList = null;
				// 全局DeserializationFeature配置
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				WaterList = objectMapper.readValue(jsonByte, Water[].class);
				// get shows info from musicShows[]
				String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(WaterList);

				//System.out.println(jsonString);
				
				request.getSession().setAttribute("WaterList", WaterList) ;
				request.getRequestDispatcher("WEB-INF/jsp/values.jsp").forward(request, response);
				
			}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
