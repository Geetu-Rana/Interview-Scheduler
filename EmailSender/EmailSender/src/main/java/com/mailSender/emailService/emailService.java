package com.mailSender.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public class emailService {
	
	private JavaMailSender jmSender;
	
	@Autowired
	public emailService(JavaMailSender jMSender) {
		// TODO Auto-generated constructor stub
		this.jmSender = jMSender;
	}
	
	
	public void semdMail(String email) throws MailException, MessagingException{
		
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setSubject("Testing mail APi");
		mail.setText("Hurrey ! Mail sent successfully");
		
		jmSender.send(mail);
	}
}
