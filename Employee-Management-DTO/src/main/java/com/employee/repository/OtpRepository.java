package com.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp,Long> {
	
	Optional<Otp> findByEmail(String email);

}
