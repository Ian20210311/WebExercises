package controller.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.util.allutil;
import dao.eduJPA;
import dao.entity.StudentInfo;

/**
 * Servlet implementation class jpatestServlet
 */
@WebServlet("/jpatestServlet")
public class jpatestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jpatestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		eduJPA jpatest = new eduJPA() ;
		
		List<StudentInfo> stdList = jpatest.getstudentbyId("F123456789");
		
		String name = "" ;
		for(StudentInfo s : stdList)
		{
			name = s.getName();
		}
		
		/*
		StudentInfo std = new StudentInfo();
		std.setStudentId("G123456789");
		std.setName("Copper");
		std.setCounty("大安區");
		std.setCity("台北市");
		std.setZip("23154");
		std.setRoad("賓夕法尼亞大道1600號");
		std.setParentMail("freddyruan@hotmail.com");
		std.setPwd_seed(allutil.get10random());
		std.setPwd_hash(allutil.getPwdHash(std.getPwd_seed(), "1234"));
		int regrtn = jpatest.saveUser(std);
		
		if(regrtn!=0)
		{
			request.setAttribute("RtnErrMsg", "新增學生失敗");
			request.getRequestDispatcher("jsp/err.jsp").forward(request, response);
		}
		//System.out.println("The regrtn = " + regrtn) ;
		 * response.getWriter().append("Served at: ").append(request.getContextPath()).append("the rtn code -> " + regrtn);
		 * 
		 */
		
		response.getWriter().append("Served at: ")
			.append(request.getContextPath())
			.append("the rtn code -> " + name);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
