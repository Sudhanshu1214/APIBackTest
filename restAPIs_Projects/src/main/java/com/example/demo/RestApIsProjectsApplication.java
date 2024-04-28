package com.example.demo;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")	
public class RestApIsProjectsApplication {

	@Autowired
	Environment env;

	public static void main(String[] args) {
		SpringApplication.run(RestApIsProjectsApplication.class, args);
	}
	
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//	    mailSender.setHost("smtp.gmail.com");	
//	    mailSender.setPort(587);
//	    mailSender.setUsername("sudhanshukumar245a@gmail.com");
//	    mailSender.setPassword("Su4h1nshu@123");
//	    
//	    Properties props = mailSender.getJavaMailProperties();
//	    props.put("mail.transport.protocol", "smtp");
//	    props.put("mail.smtp.auth", "true");
//	    props.put("mail.smtp.starttls.enable", "true");
//	    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//	    props.put("mail.debug", "true");
//	    
//	    return mailSender;
//	}

}
