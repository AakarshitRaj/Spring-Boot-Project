package com.employee.DTO;

public class RefreshTokenResponseDTO {

	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public RefreshTokenResponseDTO(String accessToken) {
		this.accessToken = accessToken;
	}

	public RefreshTokenResponseDTO() {
	}
	
}
