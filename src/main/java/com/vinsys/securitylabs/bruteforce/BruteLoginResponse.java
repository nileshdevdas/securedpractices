package com.vinsys.securitylabs.bruteforce;

import java.sql.Date;

public class BruteLoginResponse {


	private String email;
	private String jwtTokennnn;
	private String statuss;
	private boolean accountNonLocked;
	private int failedAttempt;
	 private Date lockTime;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJwtTokennnn() {
		return jwtTokennnn;
	}
	public void setJwtTokennnn(String jwtTokennnn) {
		this.jwtTokennnn = jwtTokennnn;
	}
	public String getStatuss() {
		return statuss;
	}
	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public int getFailedAttempt() {
		return failedAttempt;
	}
	public void setFailedAttempt(int failedAttempt) {
		this.failedAttempt = failedAttempt;
	}
	public Date getLockTime() {
		return lockTime;
	}
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}



}
