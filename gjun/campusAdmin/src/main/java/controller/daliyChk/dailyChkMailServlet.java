package controller.daliyChk;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dailyChk.DailyChkQueryModel;
import model.mail.SendEmail2;
import dao.DailyChkQuery;
import dao.entity.*;

/**
 * Servlet implementation class dailyChkMailServlet
 */
@WebServlet("/jsp/parents/dailyChkMailServlet")
public class dailyChkMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dailyChkMailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentInfo si=(StudentInfo)request.getSession().getAttribute("dailyChkQueryStudent");
		List<DailyChkQueryModel> data = (List<DailyChkQueryModel>) request.getSession().getAttribute("dailyChkQueryData");
		String oldmail=si.getParentMail();
		String newMail=request.getParameter("newMail");
		String mail=newMail.equals("")?oldmail:newMail;
		int result=1;
		int mailupdateresult=-99;
		String msg="";
		response.setContentType("text/html;charset=UTF-8");
		
		mailupdateresult=newMail.equals("")?-99:new DailyChkQuery().updateEmail(mail, si);
		result=new SendEmail2().doSend(mail, si, data);
		
		msg=mailupdateresult>0?msg+"電子郵件已更新為"+mail+", ":mailupdateresult==-99?"":msg+"電子郵件更新失敗, ";
		if(result==0 && (mailupdateresult==-99 || mailupdateresult>0)) {
			System.out.println("Send mail Ok");
			response.getWriter().append(msg+"郵件已寄出！<br><a href=\"selchk.jsp\"><h3>回打卡查詢</h3></a>");
		} else {
			System.out.println("Send mail false");
			response.getWriter().append(msg+"郵件尚未寄出，請聯繫系統管理員！<br><a href=\"selchk.jsp\"><h3>回打卡查詢</h3></a>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	/*
	public Multipart mailSet(StudentInfo si, List<DailyChkQueryModel> data) {
		Multipart email = new MimeMultipart();//合體
		StringBuffer mailText= new StringBuffer();//文字push
		
		try {
			//HTML
			MimeBodyPart textPart = new MimeBodyPart();
			mailText.append("<p>Hi, 有關"+si.getName()+"(編號:"+si.getStudentNo()+")的打卡紀錄如下：</p><br>");
			mailText.append("<table><tr>"
					+ "<td><h4>日期</h4></td>"
					+ "<td><h4>時間</h4></td>"
					+ "<td><h4>結果</h4></td>"
					+ "</tr>");
			for(int i=0; i<data.size(); i++) {
				mailText.append("<tr><td>"+data.get(i).getDate()+"</td>"
						+"<td>"+data.get(i).getTime()+"</td>"
						+"<td>"+data.get(i).getStatus()+"</td></tr>");
			}
			mailText.append("</table>");
			mailText.append("<img src='cid:image'/><br>");
			textPart.setContent(mailText.toString(), "text/html; charset=UTF-8");
			email.addBodyPart(textPart);
			
			//IMG
			MimeBodyPart pic = new MimeBodyPart();
			String imgSource="/WEB-INF/img/stamp.jpg";
			//FileDataSource fds = new FileDataSource(imgSource);
			byte[] imgBytes = new allutil().readlocalJSON(getServletContext().getResourceAsStream(imgSource));
			
			DataSource fds = new ByteArrayDataSource(imgBytes, "image/*");
			
			pic.setDataHandler(new DataHandler(fds));
			pic.setFileName(fds.getName());
			pic.setHeader("Content-ID", "<image>");
			email.addBodyPart(pic);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return email;
	}*/

}
