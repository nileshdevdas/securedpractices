package com.vinsys.securitylabs.weakpass;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.securitylabs.leastpriviledge.entity.User;
import com.vinsys.securitylabs.leastpriviledge.request.LoginRequest;
import com.vinsys.securitylabs.leastpriviledge.request.SignupRequest;


@Service
public class SetPasswordService {
	
	@Autowired
	
	private DataSource datasource;
	
	
	public Boolean isValidPassword(String password) {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		
		Pattern p = Pattern.compile(regex);
		
		if(password == null) {
			return false;
		}
		Matcher m = p.matcher(password);
		if(m.matches()) {
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public String changePassword(SignupRequest request) {
		User user = null;
		Connection conn =null;
		String message=null;
		try {
			
			if(isValidPassword(request.getPassword())) {
			conn=datasource.getConnection();
			String sql = "update tbl_mst_users set password =? where username =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, request.getPassword());	
			ps.setString(2,request.getUsername());
			ps.execute();
			message="Change Pasword SuccessFully..!!";
			}else {
				message= "The password must content At least 8 char Contains at least "
						+ "one digit Contains at least one lower alpha char and one upper alpha char Contains at"
						+ " least one char within a set of special chars (@#%$^ etc.)Does not contain space, tab, etc.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return message;
	}
	
	
	
	

}
