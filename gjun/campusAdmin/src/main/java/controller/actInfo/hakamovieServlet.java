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
import model.jsoninfo.hakaa;
import model.jsoninfo.hakamovie;

/**
 * Servlet implementation class hakamovieServlet
 */
@WebServlet("/hakamovieServlet")
public class hakamovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hakamovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//https://cloud.hakka.gov.tw/Pub/Opendata/DTST20190300011.json  看影片學客語
    	String filePath = "/WEB-INF/json/movie.json";
		// readlocalfile
		byte[] jsonByte = new allutil().readlocalJSON(getServletContext().getResourceAsStream(filePath));
		// read JSON file
		ObjectMapper objectMapper = new ObjectMapper();
		hakamovie [] hamovie = null;
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		hamovie = objectMapper.readValue(jsonByte, hakamovie[].class);
		System.out.println(hamovie);
		// get city info from pojoJson[]
		// String jsonString =
		// objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mci);

		request.getSession().setAttribute("hamovie", hamovie) ;
		request.getRequestDispatcher("WEB-INF/jsp/hakamovie.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
