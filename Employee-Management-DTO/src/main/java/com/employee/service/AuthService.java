package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.DTO.LoginRequestDTO;
import com.employee.DTO.LoginResponseDTO;
import com.employee.DTO.RegisterRequestDTO;
import com.employee.entity.User;
import com.employee.repository.UserRepository;
import com.employee.security.JwtUtil;

@Service
public class AuthService {
@Autowired
private UserRepository repository;

@Autowired
private PasswordEncoder passwordEncoder;

@Autowired
private JwtUtil jwtUtil;

public String register(RegisterRequestDTO dto) {
	
	if(repository.findByUsername(dto.getUsername()).isPresent()) {
		//throw new RuntimeException("Username already exists"); 
		 throw new IllegalArgumentException("Username already exists");
	}
	
	User user=new User();
	user.setUsername(dto.getUsername());
	user.setPassword(passwordEncoder.encode(dto.getPassword()));
	user.setEmail(dto.getEmail());
	user.setRole(dto.getRole());
	repository.save(user);
	
	
	return "User Register Successfully";
}

@Autowired
private AuthenticationManager authenticationManager;

public LoginResponseDTO login(LoginRequestDTO dto) {
	
	authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    dto.getUsername(),
                    dto.getPassword()
            )
    );

	
    String token =
            jwtUtil.generateToken(
                    dto.getUsername());

    return new LoginResponseDTO(token);
}
}
