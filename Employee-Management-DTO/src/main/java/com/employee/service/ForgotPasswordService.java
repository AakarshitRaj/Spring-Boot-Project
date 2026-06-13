package com.employee.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import com.employee.entity.Otp;
import com.employee.entity.User;
import com.employee.repository.*;

@Service
public class ForgotPasswordService {

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private OtpRepository otpRepository;
	
	@Autowired
	private RedisOtpService redisOtpService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String sendOtp(String email) {
		User user = userRepository.findByEmail(email)
					.orElseThrow(()->
						new RuntimeException("Email not found")
							);
		
		String otp=String.valueOf((int)(Math.random()*900000)+100000);
		
//		Otp otpEntity=new Otp();
//		
//		otpEntity.setEmail(email);
//		otpEntity.setOtp(otp);
//		
//		otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
		
		//otpRepository.save(otpEntity);
		
		//For Redis
		redisOtpService.saveOtp(email, otp);
		
		emailService.sendOtpEmail(email,otp);
		
		return "OTP Sent Successfully";
		
	}
	
	public String verifyOtp(String email,String otp) {
//		Otp savedOtp = otpRepository.findByEmail(email)
//									.orElseThrow(()->
//										new RuntimeException("OTP Not Found")
//											);
//		
//		if(!savedOtp.getOtp().equals(otp)) {
//			throw new RuntimeException("Invalid OTP");
//		}
//		
//		if(savedOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
//			throw new RuntimeException("OTP Expired");
//		}
//		return "OTP Verified";
		
		String savedOtp= redisOtpService.getOtp(email);
		
		if(savedOtp == null) {
			throw new RuntimeException("OTP Expired");
		}
		
		if(!savedOtp.equals(otp)) {
			throw new RuntimeException("Invalid OTP");
		}
		
		redisOtpService.deleteOtp(email);
		
		return "OTP Verified";
	}
	
	public String resetPassword(String email,String newPassword) {
		User user = userRepository.findByEmail(email)
						.orElseThrow(()->
							new RuntimeException("User Not Found")
								);
		
		user.setPassword(passwordEncoder.encode(newPassword));
		
		userRepository.save(user);
		
		return "Password Updated";
	}
}
