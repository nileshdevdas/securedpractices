package com.vinsys.securitylabs.enumeration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.securitylabs.brokenauth.User;


@Service
public class EnumerationService {
	
	
	@Autowired
	private DataSource dataSource;
	
	public boolean existByEmail(String username) {
		User user = null;
		Connection con = null;
		 boolean isAvailable = false;
		try {
			con = dataSource.getConnection();
			String sql = "select * from tbl_sec_users where email ='"+username+"'";
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()){
                isAvailable = true;
            }else{
                isAvailable = false;
        }
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return isAvailable;
	}
	
	
	public boolean existByPassword(String password) {
		User user = null;
		Connection con = null;
		 boolean isAvailable = false;
		try {
			con = dataSource.getConnection();
			String sql = "select * from tbl_sec_users where password ='"+password+"'";
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()){
                isAvailable = true;
            }else{
                isAvailable = false;
        }
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return isAvailable;
	}
	
}
