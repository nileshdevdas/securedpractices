package com.vinsys.securitylabs.cleartextdatatransmission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbl_encrypted_data", 
	uniqueConstraints = {
		@UniqueConstraint(columnNames = "ecryptedusername"),
		@UniqueConstraint(columnNames = "encryptedPass")
	})
public class EncryptedDataEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Size(max=20)
	private String ecryptedusername;
	
	@NotBlank
	@Size(max=20)
	private String encryptedPass;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEcryptedusername() {
		return ecryptedusername;
	}

	public void setEcryptedusername(String ecryptedusername) {
		this.ecryptedusername = ecryptedusername;
	}

	public String getEncryptedPass() {
		return encryptedPass;
	}

	public void setEncryptedPass(String encryptedPass) {
		this.encryptedPass = encryptedPass;
	}
	

}
