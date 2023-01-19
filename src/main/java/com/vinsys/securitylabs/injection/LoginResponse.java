package com.vinsys.securitylabs.injection;

public class LoginResponse {

	private String email;
	private String jwtToken;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
