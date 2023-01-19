package com.vinsys.securitylabs.tokenExpiration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_user_activity")
public class Userdata {
	
	@Id
	@SequenceGenerator(name="seq_user", sequenceName = "seq_user", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_user")
	 private long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="tokenId")
	private String tokenId;
	
	@Column(name="is_active")
	private String isActive;
	
	@Column(name="is_first_login")
	private String isFirstLogin;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


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

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getIs_active() {
		return isActive;
	}

	public void setIs_active(String is_active) {
		this.isActive = is_active;
	}

	
	public String getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(String isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public Userdata(String username, String password, String tokenId) {
		super();
		this.username = username;
		this.password = password;
		this.tokenId = tokenId;
	}

	public Userdata() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
