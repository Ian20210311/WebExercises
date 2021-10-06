package controller.actInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.util.allutil;
import model.jsoninfo.allcar;

/**
 * Servlet implementation class carServlet
 */
@WebServlet("/carServlet")
public class carServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public carServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonurl="https://tcgbusfs.blob.core.windows.net/dotapp/news.json";
		String jsonStrweb = new allutil().readJson(jsonurl);
		String filePath = "/WEB-INF/json/car.json";
		
		// readlocalfile
		byte[] jsonByte = new allutil().readlocalJSON(getServletContext().getResourceAsStream(filePath));
		String jsonStr = new String(jsonByte, java.nio.charset.StandardCharsets.UTF_8);
		// read JSON file
		ObjectMapper objectMapper = new ObjectMapper();
		allcar data = null;
		//car = objectMapper.readValue(jsonByte, allcar.class);
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		
		data = gson.fromJson(jsonStr, allcar.class);
		//data = gson.fromJson(jsonStr, allcar.class);
		// get city info from pojoJson[]
		String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);

		request.setAttribute("data", data);
		request.getRequestDispatcher("WEB-INF/jsp/car.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
