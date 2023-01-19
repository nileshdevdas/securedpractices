package com.vinsys.securitylabs.cleartextdatatranmission.solution;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.log.SysoCounter;

import com.vinsys.securitylabs.cleartextdatatransmission.DataEntity;
import com.vinsys.securitylabs.cleartextdatatransmission.EncryptedDataEntity;

@Service
public class ClearTextDataTransmissionSolutionService {

	@Autowired
	private DataSource ds;
	
	
	public EncryptedDataEntity encryptedDataTransmission( String ecryptedusername,String encryptedPass) {
	
		EncryptedDataEntity ede=null;
		Connection con=null;
		 
		 try {
			 MessageDigest m =MessageDigest.getInstance("MD5");
			  
			 m.update(ecryptedusername.getBytes());
			 m.update(encryptedPass.getBytes());
	
			 byte[] bytes=m.digest();
			 
			 StringBuilder s= new StringBuilder();
			 for(int i=0;i<bytes.length;i++)
			 {
				  s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1)); 
				  encryptedPass=s.toString();
				  ecryptedusername=s.toString();
			 }
		 }catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		 System.out.println("Encrypted Username"+ecryptedusername);
		 System.out.println("Encryped PassCode"+encryptedPass);
		 
		 
		return ede;
	}
	
}
