package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.DTO.LoginRequestDTO;
import com.employee.DTO.LoginResponseDTO;
import com.employee.DTO.RefreshTokenRequestDTO;
import com.employee.DTO.RefreshTokenResponseDTO;
import com.employee.DTO.RegisterRequestDTO;
import com.employee.entity.RefreshToken;
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

@Autowired
private RefreshTokenService refreshTokenService;

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
	  System.out.println("STEP 1: Login method called");
	
	authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    dto.getUsername(),
                    dto.getPassword()
            )
    );

	System.out.println("STEP 2: Authentication successful");
	
//    String token =
//            jwtUtil.generateToken(
//                    dto.getUsername());
	String accessToken=jwtUtil.generateToken(dto.getUsername());
	System.out.println("STEP 3: JWT generated");
	
	RefreshToken refreshToken=refreshTokenService.createRefreshToken(dto.getUsername());
	System.out.println("STEP 4: Refresh token generated");

//    return new LoginResponseDTO(token);
	return new LoginResponseDTO(accessToken,refreshToken.getToken());
}

public RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO request) {
	RefreshToken refreshToken=refreshTokenService.verifyRefreshToken(request.getRefreshToken());
	
	String accessToken=jwtUtil.generateToken(refreshToken.getUsername());
	
	return new RefreshTokenResponseDTO(accessToken);
}
}
