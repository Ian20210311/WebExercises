package model.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public int doSend(String toEmail) {
		int rtncode = 0;

		Properties prop = new Properties();

		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("campusAdmin.properties"));

			String username = prop.getProperty("mail.smtp.user");
			String password = prop.getProperty("mail.smtp.password");

			prop.put("mail.smtp.host", prop.getProperty("mail.smtp.host"));
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
			message.setSubject("Testing Gmail TLS");
			message.setText("Dear 閃亮亮家長," + "\n\n 你的小孩爛透了，你知道嗎??");

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
}
