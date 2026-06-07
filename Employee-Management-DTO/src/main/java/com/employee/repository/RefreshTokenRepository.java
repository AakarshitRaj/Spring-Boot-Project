package com.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository <RefreshToken,Long> {
	
	Optional<RefreshToken> findByToken(String token);

}
