package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.employee.DTO.ForgotPasswordRequestDTO;
import com.employee.DTO.ResetPasswordDTO;
import com.employee.DTO.VerifyOtpDTO;
import com.employee.service.ForgotPasswordService;

@RestController
@RequestMapping("/password")
public class PasswordController {

	@Autowired
	private ForgotPasswordService service;
	
	@PostMapping("/forgot")
	public String forgotPassword(@RequestBody ForgotPasswordRequestDTO dto) {
		
		return service.sendOtp(dto.getEmail());
	}
	@PostMapping("/verify")
	public String verifyOtp(@RequestBody VerifyOtpDTO dto){

	    return service.verifyOtp(
	            dto.getEmail(),
	            dto.getOtp());
	}
	
	@PostMapping("/reset")
	public String resetPassword(@RequestBody ResetPasswordDTO dto){

	    return service.resetPassword(
	            dto.getEmail(),
	            dto.getNewPassword());
	}
}
