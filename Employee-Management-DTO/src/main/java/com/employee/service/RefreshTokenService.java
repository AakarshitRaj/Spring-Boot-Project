package com.employee.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.RefreshToken;
import com.employee.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {
	@Autowired
	private RefreshTokenRepository repository;
	
	public RefreshToken createRefreshToken(String username) {
		RefreshToken refreshToken=new RefreshToken();
		
		refreshToken.setUsername(username);
		refreshToken.setToken(
				UUID.randomUUID().toString()
				);
		
		refreshToken.setExpiryDate(
				Instant.now().plusSeconds(7*24*60*60)
				);
		
		return repository.save(refreshToken);
	}
	
	public RefreshToken verifyRefreshToken(String token) {
		
		RefreshToken refreshToken=repository.findByToken(token)
									.orElseThrow(()->
										new RuntimeException("Refresh Token Not Found")
											);
		
		if(refreshToken.getExpiryDate().isBefore(Instant.now())) {
			repository.delete(refreshToken);
			
			throw new RuntimeException("Refresh Token Expired");
		}
		
		return refreshToken;
	}
}
