package com.vinsys.securitylabs.avoiddataduplication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="register_user")
public class userEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="seq_user", sequenceName = "seq_user", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_user")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="contact_no")
	private String contact_no;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail_id() {
		return emailId;
	}
	public void setEmail_id(String email_id) {
		this.emailId = email_id;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	
	

}
