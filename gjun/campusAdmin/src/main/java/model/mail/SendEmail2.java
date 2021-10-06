package model.mail;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import controller.util.allutil;
import dao.entity.StudentInfo;
import model.dailyChk.DailyChkQueryModel;

public class SendEmail2 {
	public int doSend(String toEmail, StudentInfo si, List<DailyChkQueryModel> data) {
		int rtncode = 0;
		
		

		Properties prop = new Properties();

		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("campusAdmin.properties"));

			//String username = prop.getProperty("mail.smtp.user");
			//String password = prop.getProperty("mail.smtp.password");
			String username="rty23030411@yahoo.com.tw";
			String password="ibrrlrqtkvtytrtx";
			String host="smtp.mail.yahoo.com";

			//prop.put("mail.smtp.host", prop.getProperty("mail.smtp.host"));
			prop.put("mail.smtp.host", host);
			prop.put("mail.smtp.port", prop.getProperty("mail.smtp.port"));
			prop.put("mail.smtp.auth", prop.getProperty("mail.smtp.auth"));
			prop.put("mail.smtp.starttls.enable", prop.getProperty("mail.smtp.starttls.enable")); // TLS

			Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));
			message.setSubject("閃亮亮校園系統打卡查詢結果");
			//message.setText(mailText);
			message.setContent(mailSet(si,data));

			Transport.send(message);

		} catch (MessagingException e) {
			rtncode = -1;
			e.printStackTrace();
		} catch (IOException ioe) {
			rtncode = -1;
			ioe.printStackTrace();
		}

		return rtncode;
	}
	
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
			String imgSource="../img/stamp.jpg";
			byte[] imgBytes = new allutil().readlocalJSON(SendEmail2.class.getClassLoader().getResourceAsStream(imgSource));
			
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
	}
}
