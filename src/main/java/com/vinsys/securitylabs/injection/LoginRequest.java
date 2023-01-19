package com.vinsys.securitylabs.injection;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class LoginRequest {

	@Email(message = "Invalid Email Id")
	private String email;

	@Pattern(regexp = "[0-9a-zA-Z#@!$&*]*$", message = "Only Following are allowed 0-9a-zA-Z#@!$&*")
	private String password;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
