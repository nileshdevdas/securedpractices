package com.vinsys.securitylabs.inputvalid;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class registerUserService {
	
	@Autowired
	private DataSource datasource;
	
	public RegisterUser Register(String name, String email_id, String contact_no) {
		
		RegisterUser user = null;
		Connection con = null;
		
		try {
			con=datasource.getConnection();
			String Sql = "insert into register_user (name,email_id,contact_no) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(Sql);
			ps.setString(1, name);
			ps.setString(2, email_id);
			ps.setString(3, contact_no);
			ps.execute();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(con!=null) {
				try {
					con.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return user;
		
	}
	
	

}
