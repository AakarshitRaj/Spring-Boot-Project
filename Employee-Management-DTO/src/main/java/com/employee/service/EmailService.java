package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public String sendEmail(String to,String subject,String body) {
		SimpleMailMessage message=new SimpleMailMessage();
		
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
		
		return "Email Sent Successfully";
	}
	
	public void sendOtpEmail(String email,String otp) {
		SimpleMailMessage message= new SimpleMailMessage();
		
		message.setTo(email);
		message.setSubject("Password Reset OTP");
		message.setText("Your OTP is"+ otp);
		
		mailSender.send(message);
	}
}
