package com.vinsys.securitylabs.leastpriviledge.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "tbl_mst_users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final long PASSWORD_EXPIRATION_TIME
    = 30L * 24L * 60L * 60L * 1000L;    // 30 days
	   
	    public String isPasswordExpired() {
//	        if (this.passwordChangedTime == null) return false;
	         
	        long currentTime = System.currentTimeMillis();
	        long lastChangedTime = passwordChangedTime.getTime();
	         if(currentTime > lastChangedTime + PASSWORD_EXPIRATION_TIME) {
	        	 return "Expired";
	         } else return "NOT Expired";
//	        return currentTime > lastChangedTime + PASSWORD_EXPIRATION_TIME;
	    }
	    
		public Date getPasswordChangedTime() {
			return passwordChangedTime;
		}

		public void setPasswordChangedTime(Date passwordChangedTime) {
			this.passwordChangedTime = passwordChangedTime;
		}

		private Date passwordChangedTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	private String isFirstLogin;
	private String isActive;
	
	
	@Size(max=4)
	private String user_captcha;



	public String getUser_captcha() {
		return user_captcha;
	}

	public void setUser_captcha(String user_captcha) {
		this.user_captcha = user_captcha;
	}

	public String getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(String isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
