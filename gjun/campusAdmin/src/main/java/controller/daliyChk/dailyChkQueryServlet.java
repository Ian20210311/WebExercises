package controller.daliyChk;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.util.allutil;
import dao.DailyChkQuery;
import dao.implDailyChkQuery;
import dao.entity.DailyChk;
import dao.entity.StudentInfo;
import model.dailyChk.DailyChkQueryModel;

/**
 * Servlet implementation class dailyChkQueryServlet
 */
@WebServlet("/jsp/parents/dailyChkQueryServlet")
public class dailyChkQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dailyChkQueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//輸入資料set
		System.out.println("Servlet In");
		String pwin=request.getParameter("pw");
		String idin=request.getParameter("id");
		//String pwin="1234", idin="A123456789";
		String msg="";
				
		//用idin去抓資料庫資料，if沒抓到資料，返回帳號或密碼有誤，if有，抓出資料庫此帳號轉為物件
		List<StudentInfo> list=new DailyChkQuery().getStudentInfo(idin);
		if(list.isEmpty()) {
			System.out.println("id err");
			msg = "帳號或密碼有誤，請重新輸入";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("dailyChkErr.jsp").forward(request, response);
		} else {
			StudentInfo si=list.get(0);
			//抓出並將pedSeed+輸入密碼帶入算式得到暗碼
			String pwdHash = allutil.getPwdHash(si.getPwd_seed(), pwin);
			//比對密碼是否正確，if不對，返回帳號或密碼有誤，if對，則Query打卡資料出來傳至jsp
			if(pwdHash.equals(si.getPwd_hash())) {
				System.out.println("pwOk");
				List<DailyChk> data=si.getDailyChks();
				
				//test
				List<DailyChkQueryModel> data2 = changeDailyChk(data);
				//test end
				
				request.setAttribute("data", data2);
				request.setAttribute("student", si);
				request.getSession().setAttribute("dailyChkQueryData", data2);
				request.getSession().setAttribute("dailyChkQueryStudent", si);
				request.getRequestDispatcher("DailyChkQuery.jsp").forward(request, response);
				
				System.out.print("Successful, ");
			} else {
				System.out.println("pw err");
				msg = "帳號或密碼有誤，請重新輸入";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("dailyChkErr.jsp").forward(request, response);
			}
			
		}
		System.out.println("Servlet End");
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected List<DailyChkQueryModel> changeDailyChk(List<DailyChk> data){
		List<DailyChkQueryModel> list = new ArrayList<DailyChkQueryModel>();
		Properties prop = new Properties();
		LocalTime checkTime=null;
		
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("campusAdmin.properties"));
			DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("HH:mm:ss");
			checkTime=LocalTime.parse(prop.getProperty("school.time.toschool"), formatter);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i<data.size();i++) {
			DailyChkQueryModel temp= new DailyChkQueryModel();
			LocalDateTime tempDT=data.get(i).getChkInTime().toLocalDateTime();
			temp.setChktime(checkTime);
			temp.setDailyNo(data.get(i).getDailyNo());
			temp.setDate(tempDT.toLocalDate());
			temp.setTime(tempDT.toLocalTime());
			temp.setStatus();
			list.add(temp);
		}
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * Old databass version
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//輸入資料set
				System.out.println("Servlet In");
				String pwin=request.getParameter("pw");
				String idin=request.getParameter("id");
				//String pwin="1234", idin="A123456789";
				String msg="";
				
				
				//DCquery(response, "SELECT d FROM DailyChk d");
				//用idin去抓資料庫資料，if沒抓到資料，返回帳號或密碼有誤，if有，抓出資料庫此帳號轉為物件
				List<StudentInfo> list=studentIdCheck(idin);
				if(list.isEmpty()) {
					//response.getWriter().append("帳號或密碼有誤，請重新輸入");
					//response.getWriter().print("<script> alert(\"帳號或密碼有誤，請重新輸入!\"); </script>");
					System.out.println("id err");
					msg = "帳號或密碼有誤，請重新輸入";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("dailyChkErr.jsp").forward(request, response);
				} else {
					StudentInfo si=list.get(0);
					//抓出並將pedSeed+輸入密碼帶入算式得到暗碼
					String pwdHash = allutil.getPwdHash(si.getPwd_seed(), pwin);
					//比對密碼是否正確，if不對，返回帳號或密碼有誤，if對，則Query打卡資料出來傳至jsp
					//String url="/jsp/parents/DailyChkQuery2.jsp";
					//String url="DailyChkQuery2.jsp";
					String url="DailyChkQuery.jsp";
					if(pwdHash.equals(si.getPwd_hash())) {
						System.out.println("pwOk");
						String jpql="SELECT d FROM DailyChk d JOIN d.studentInfo s where s.studentId = '"+studentIdCheck(idin).get(0).getStudentId()+"'";
						List<DailyChk> data=DCquery(response, jpql);
						request.setAttribute("data", data);
						request.getRequestDispatcher(url).forward(request, response);
						
						System.out.print("Successful, ");
					} else {
						System.out.println("pw err");
						msg = "帳號或密碼有誤，請重新輸入";
						request.setAttribute("msg", msg);
						request.getRequestDispatcher("dailyChkErr.jsp").forward(request, response);
					}
					
				}
				System.out.println("Servlet End");
				
				//String idin = "A123456789";
				//DCinsert(response,"A123456789");//新增打卡ok，真的要用要另外弄一個Servlet比較好
				//DCquery(response, "SELECT d FROM DailyChk d");//查全部ok
				//DCquery(response, "SELECT d FROM DailyChk d where s.dailyNo = '2'");//查編號ok
				//DCquery(response, "SELECT d FROM DailyChk d JOIN d.studentInfo s where s.studentId = '"+studentIdCheck(idin).get(0).getStudentId()+"'");//依照student id Query
				//test();
				
				//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected List<DailyChk> DCquery(HttpServletResponse response,String request) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("eduAdmin_jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//<tr><td><h4>學生姓名: <c:out value="${data.getStudentInfo().getName()}"/></h4></td></tr>
		Query query = em.createQuery(request);
		@SuppressWarnings("unchecked")
		List<DailyChk> list = (List<DailyChk>)query.getResultList();
		
		//System.out.print("DailyNo");
		//System.out.print("\t StudentInfo");
		//System.out.print("\t ChkInTime\n");
		//for (DailyChk s:list) {
		//	response.getWriter().append(" "+s.getDailyNo());
		//	response.getWriter().append(" "+s.getStudentInfo().getStudentId());
		//	response.getWriter().append(" "+s.getChkInTime()+"<br>");
		//}
		//response.getWriter().append("ok<br>");
		
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return list;
	}
	
	protected void DCinsert(HttpServletResponse response, String studentId) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("eduAdmin_jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		//StudentInfo si = new StudentInfo();
		//si.setStudentId("A123456789");
		
		DailyChk dci = new DailyChk();
		List<StudentInfo> list=studentIdCheck(studentId);
		if(list.isEmpty()) {
			response.getWriter().append("查無此帳號<br>");
		} else {
			dci.setStudentInfo(list.get(0));
			//dci.setChkInTime(null);
			//dci.setDailyNo(4);
			em.persist(dci);
			
			em.getTransaction().commit();
			em.close();
			emf.close();
			//response.getWriter().append("ok<br>");
		}
	}
	protected void test() {
		StudentInfo data = studentIdCheck("A123456789").get(0);
		System.out.println(data.getStudentId()+data.getName());
	}
	
	protected List<StudentInfo> studentIdCheck(String id) {
		String check="SELECT s FROM StudentInfo s where s.studentId like '"+id+"'";
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("eduAdmin_jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery(check);
		List<StudentInfo> list = (List<StudentInfo>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return list;
	}
	*/

}
