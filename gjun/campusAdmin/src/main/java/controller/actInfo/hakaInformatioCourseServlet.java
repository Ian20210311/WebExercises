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
import model.jsoninfo.Center_service_content_info;
import model.jsoninfo.hakaInformatioCourse;

/**
 * Servlet implementation class hakaInformatioCourseServlet
 */
@WebServlet("/hakaInformatioCourseServlet")
public class hakaInformatioCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hakaInformatioCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//https://cloud.hakka.gov.tw/Pub/Opendata/DTST20200100078.json 資訊課程課表
		String filePath = "/WEB-INF/json/InformationCourse.json";
		// readlocalfile
		byte[] jsonByte = new allutil().readlocalJSON(getServletContext().getResourceAsStream(filePath));
		// read JSON file
		ObjectMapper objectMapper = new ObjectMapper();
		hakaInformatioCourse [] infor = null;
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		infor = objectMapper.readValue(jsonByte, hakaInformatioCourse[].class);
		//System.out.println(infor);
		// get city info from pojoJson[]
		// String jsonString =
		// objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mci);

		request.getSession().setAttribute("infor", infor) ;
		request.getRequestDispatcher("WEB-INF/jsp/hakaInformatioCourse_1.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
