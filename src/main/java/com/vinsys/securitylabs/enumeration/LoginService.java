package com.vinsys.securitylabs.enumeration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.securitylabs.injection.LoginRequest;
import com.vinsys.securitylabs.injection.LoginResponse;
import com.vinsys.securitylabs.injection.UserExistResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {
	

	private static final String key = "ABCDEFGHNILESHDEVDAS!@#@#@#@#@#ABCDEFGHNILESHDEVDAS!@#@#@#@#@#ABCDEFGHNILESHDEVDAS!@#@#@#@#@#";
	@Autowired
	private DataSource dataSource;
	private static final String FAILED = "FAILED";
	private static final String SUCCESS = "SUCCESS";

	public LoginResponse login(LoginRequest request) {
		LoginResponse response = new LoginResponse();
		Connection con = null;
		try {
			String sql = "select * from tbl_sec_users where email = '" + request.getEmail() + "' and password =  '"
					+ request.getPassword() + "'";
			System.out.println(sql);
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				Calendar cl = Calendar.getInstance();
				cl.add(Calendar.MINUTE, 30);
				String token = Jwts.builder().claim("email", request.getEmail()).setSubject(request.getEmail())
						.setExpiration(cl.getTime()).signWith(SignatureAlgorithm.HS512, key.getBytes()).compact();
				response.setJwtToken(token);
				response.setStatus(SUCCESS);
				response.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(FAILED);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return response;
	}

	public UserExistResponse checkUser(String email) {
		Connection con = null;
		UserExistResponse response = new UserExistResponse();
		try {
			List<String> data = new ArrayList<>();
			String sql = "select * from tbl_sec_users where email = '" + email + "' ";
			System.out.println(sql);
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				data.add(rs.getString("email"));
			}
			response.setUsers(data);
			response.setStatus(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(FAILED);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return response;
	}

	

}

