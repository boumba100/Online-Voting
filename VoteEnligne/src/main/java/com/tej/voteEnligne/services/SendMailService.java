package com.tej.voteEnligne.services;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class SendMailService {
	private static final String FROM_EMAIL = "VoteEnligneFO@gmail.com";
	private static final String PASSWORD = "VoteEnlignePassword9876";
	private Properties properties;
	private Session session;
	
	public SendMailService() {
		this.properties = System.getProperties();
		this.properties.put("mail.smtp.auth", "true");
		this.properties.put("mail.smtp.starttls.enable", "true");
		this.properties.put("mail.smtp.host", "smtp.gmail.com");
		this.properties.put("mail.smtp.port", "587");
		this.session = session = Session.getInstance(this.properties,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
					}
				  });
	}
	
	public void sendEmail(String email, String subject, String body) {
	    try {
	    	Message message = new MimeMessage(this.session);
			message.setFrom(new InternetAddress(FROM_EMAIL));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}