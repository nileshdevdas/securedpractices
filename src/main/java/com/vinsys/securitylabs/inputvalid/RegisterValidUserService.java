package com.vinsys.securitylabs.inputvalid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterValidUserService {
	
	@Autowired
	
	private DataSource datasource;
	
	
	public boolean isvalidInput(String name, String email_id, String contact_no) {
		
		String regex1 = "^[a-zA-Z]+$";
		
		String regex2 = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		
		String regex3 = "^[0-9,+][0-9]{9,13}$";
		
		Pattern p1 = Pattern.compile(regex1);
		Pattern p2 = Pattern.compile(regex2);
		Pattern p3 = Pattern.compile(regex3);
		if(name==null && email_id==null && contact_no==null) {
			return false;
		}
		
		Matcher m1 = p1.matcher(name);
		
		Matcher m2 = p2.matcher(email_id);
		
		Matcher m3 = p3.matcher(contact_no);
		
		if(m1.matches() && m2.matches() && m3.matches()) {
			return true;
		}
		
		else {
			return false;
		}
		
	}
	
	public String RegisteredUser(RegisterUser request) {
		RegisterUser user = null;
		Connection conn = null;
		String message = null;
		
		try {
			if(isvalidInput(request.getName(), request.getEmail_id(), request.getContact_no())) {
				conn = datasource.getConnection();
				String sql = "insert into register_user (name,email_id,contact_no) values(?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, request.getName());
				ps.setString(2, request.getEmail_id());
				ps.setString(3, request.getContact_no());
				ps.execute();
				message="User Registered successfully....";
			}
			else {
				message = "You hvae entered invalid information, kindly entered valid data";
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return message;
	}
	

}
