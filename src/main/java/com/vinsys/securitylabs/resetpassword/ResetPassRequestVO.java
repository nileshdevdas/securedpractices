package com.vinsys.securitylabs.resetpassword;

import com.vinsys.securitylabs.leastpriviledge.entity.AuditBase;

public class ResetPassRequestVO extends AuditBase {
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String newPassword;
	private String currentVersion;
	private String gpsLogin;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	public String getGpsLogin() {
		return gpsLogin;
	}
	public void setGpsLogin(String gpsLogin) {
		this.gpsLogin = gpsLogin;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	 

}
