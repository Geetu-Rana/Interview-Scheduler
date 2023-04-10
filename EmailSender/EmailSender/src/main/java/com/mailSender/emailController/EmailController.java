package com.mailSender.emailController;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

import jakarta.annotation.PostConstruct;

public class EmailController {
		@Value("${gmail.clientId}")
	    private String clientId;

	    @Value("${gmail.clientSecret}")
	    private String clientSecret;

	    @Value("${gmail.accessToken}")
	    private String accessToken;

	    @Value("${gmail.refreshToken}")
	    private String refreshToken;

	    private static final String APPLICATION_NAME = "My Gmail API App";
	    private static final JsonFactory JSON_FACTORY = Jackson2ObjectMapperFactoryBean.getDefaultInstance();
	    private static final String USER = "me";
	    private static final String SCOPE = GmailScopes.GMAIL_SEND;

	    private HttpTransport httpTransport;
	    private Gmail gmail;

	    @PostConstruct
	    public void init() throws GeneralSecurityException, IOException {
	        httpTransport = GoogleNetHttpTransport.newTrustedTransport();

	        GoogleCredential credential = new GoogleCredential.Builder()
	                .setJsonFactory(JSON_FACTORY)
	                .setTransport(httpTransport)
	                .setClientSecrets(clientId, clientSecret)
	                .build()
	                .setAccessToken(accessToken)
	                .setRefreshToken(refreshToken);

	        if (credential.getExpiresInSeconds() <= 0) {
	            credential.refreshToken();
	        }

	        gmail = new Gmail.Builder(httpTransport, JSON_FACTORY, credential)
	                .setApplicationName(APPLICATION_NAME)
	                .build();
	    }

	    @GetMapping("/emailAPI")
	    @ResponseBody
	    public String sendEmail(@RequestParam String to) throws Exception {
	        String subject = "Test Email";
	        String body = "This is a test email sent from my Spring Boot app.";

	        try {
	            MimeMessage mimeMessage = createEmail(to, subject, body);
	            sendMessage(mimeil(mimeMessage));
	            return "Email sent to " + to;
	        } catch (MessagingException e) {
	        throw new Exception("Failed to send email to " + to, e);
	        }
	        }

		private MimeMessage createEmail(String to, String subject, String body) {
			// TODO Auto-generated method stub
			return null;
		}

		private void sendMessage(Object mimeil) {
			// TODO Auto-generated method stub
			
		}

		private Object mimeil(MimeMessage mimeMessage) {
			// TODO Auto-generated method stub
			return null;
		}
	    
}
