package com.vinsys.securitylabs.injection;

import javax.validation.constraints.Email;

public class UserExistRequest {
	@Email(message = "Valid email")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
