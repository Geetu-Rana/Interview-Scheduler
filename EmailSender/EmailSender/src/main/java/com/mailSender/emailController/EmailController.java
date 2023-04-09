package com.mailSender.emailController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mailSender.emailService.emailService;

import jakarta.mail.MessagingException;

@RestController
public class EmailController {
	  
	  private emailService eServ;
	  
	  @Autowired
	  public EmailController(emailService emailService){
		  this.eServ = emailService;
	  }
	  
	  @GetMapping("/emailAPI")
	  public String sendMail(@RequestParam("to") String email) {
		  
		  try {
			eServ.semdMail(email);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  return "Mail sent succesfully to "+email;
	  }
	  

}
