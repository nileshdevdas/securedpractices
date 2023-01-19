package com.vinsys.securitylabs.brokenauth;

import javax.validation.constraints.Pattern;

public class User {
	private Long id;
	private String email;
	private String password;
	@Pattern(regexp = "[^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}]$", message = "The password must content :\r\n"
			+ "\r\n"
			+ "At least 8 chars\r\n"
			+ "\r\n"
			+ "Contains at least one digit\r\n"
			+ "\r\n"
			+ "Contains at least one lower alpha char and one upper alpha char\r\n"
			+ "\r\n"
			+ "Contains at least one char within a set of special chars (@#%$^ etc.)\r\n"
			+ "\r\n"
			+ "Does not contain space, tab, etc.")
	
	private String firstName;
	private String lastName;
	private String GUID;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGUID() {
		return GUID;
	}
	public void setGUID(String gUID) {
		GUID = gUID;
	}
}
