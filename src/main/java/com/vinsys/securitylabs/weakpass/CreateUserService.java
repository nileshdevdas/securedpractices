package com.vinsys.securitylabs.weakpass;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.securitylabs.leastpriviledge.entity.User;
@Service
public class CreateUserService {

	@Autowired
	private DataSource datasource;
	
	public User createUser(String email, String password, String username, String is_active, String is_first_login, Date password_changed_time) {
		
		User user = null;
		Connection con = null;
		try {
			con = datasource.getConnection();
			String sql = "insert into tbl_mst_users(email,password,username,is_active,is_first_login,password_changed_time) values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, username);
			ps.setString(4, is_active);
			ps.setString(5, is_first_login);
			ps.setDate(6, (java.sql.Date) password_changed_time);
			 ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return user;
	}	
	
}
