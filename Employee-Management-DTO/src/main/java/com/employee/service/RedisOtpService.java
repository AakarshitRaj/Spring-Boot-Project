package com.employee.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisOtpService {
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	public void saveOtp(String email,String otp) {
		
		System.out.println("Saving OTP in Redis");
	    System.out.println("Email: " + email);
	    System.out.println("OTP: " + otp);

	    redisTemplate.opsForValue()
	            .set(email, otp, 5, TimeUnit.MINUTES);

	    System.out.println("OTP Stored Successfully");
	}
	
	public String getOtp(String email) {
		
		Object otp=redisTemplate.opsForValue().get(email);
		
		return otp == null ? null : otp.toString();
	}
	
	public void deleteOtp(String email) {
		redisTemplate.delete(email);
	}
	
}
