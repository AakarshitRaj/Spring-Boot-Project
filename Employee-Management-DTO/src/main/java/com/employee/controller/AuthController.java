package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.employee.DTO.LoginRequestDTO;
import com.employee.DTO.LoginResponseDTO;
import com.employee.DTO.RefreshTokenRequestDTO;
import com.employee.DTO.RefreshTokenResponseDTO;
import com.employee.DTO.RegisterRequestDTO;
import com.employee.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService service;
	
	@PostMapping("/register")
	public String register(@RequestBody RegisterRequestDTO dto) {
		return service.register(dto);
	}
	
	@PostMapping("/login")
	public LoginResponseDTO login(
	        @RequestBody LoginRequestDTO dto) {

	    return service.login(dto);
	}
	
	@PostMapping("/refresh")
	public RefreshTokenResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO request) {
		return service.refreshToken(request);
	}
}
