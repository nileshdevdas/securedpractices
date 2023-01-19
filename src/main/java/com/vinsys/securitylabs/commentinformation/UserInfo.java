package com.vinsys.securitylabs.commentinformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="register_user")
public class UserInfo {
	
	private static final long serialVersionUID =1L;
	
	@Id
	@SequenceGenerator(name="seq_userinfo", sequenceName="seq_userinfo",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_userinfo")
	private long id;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="email_id")
	private String EmailId;
	
	@Column(name="contact_no")
	private String ContactNo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmailId() {
		return EmailId;
	}

	public void setEmailId(String emailId) {
		EmailId = emailId;
	}

	public String getContactNo() {
		return ContactNo;
	}

	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}
	

}
