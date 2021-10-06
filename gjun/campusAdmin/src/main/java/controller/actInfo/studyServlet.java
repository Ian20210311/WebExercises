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
import model.jsoninfo.Study;
import model.jsoninfo.pigDetail;

/**
 * Servlet implementation class studyServlet
 */
@WebServlet("/studyServlet")
public class studyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 客語結合十二年國教校訂課程
				String filePath = "/WEB-INF/json/study.json";
				//String filePath = "/WEB-INF/json/xxx.json";
				
				// readlocalfile
				byte[] jsonByte = new allutil().readlocalJSON(getServletContext().getResourceAsStream(filePath));
				// read JSON file
				ObjectMapper objectMapper = new ObjectMapper();
				Study[] studyList = null;
				// 全局DeserializationFeature配置
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				studyList = objectMapper.readValue(jsonByte, Study[].class);
				// get shows info from musicShows[]
				String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(studyList);

				//System.out.println(jsonString);
				
				request.getSession().setAttribute("studylist", studyList) ;
				request.getRequestDispatcher("WEB-INF/jsp/study.jsp").forward(request, response);
				
			}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
