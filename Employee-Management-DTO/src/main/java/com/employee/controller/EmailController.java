package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.employee.DTO.EmailRequestDTO;
import com.employee.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send")
	public String sendEmail(@RequestBody EmailRequestDTO dto) {
		
		return emailService.sendEmail(
				dto.getTo(),
				dto.getSubject(),
				dto.getBody()
		);
	}
}
