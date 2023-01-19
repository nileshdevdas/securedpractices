package com.vinsys.securitylabs.injection;

import java.util.List;

public class UserExistResponse {

	private List<String> users;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

}
