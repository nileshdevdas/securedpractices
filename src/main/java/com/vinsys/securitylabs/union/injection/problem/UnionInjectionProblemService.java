package com.vinsys.securitylabs.union.injection.problem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.securitylabs.injection.UserExistResponse;

@Service
public class UnionInjectionProblemService {
	@Autowired
	private DataSource dataSource;
	private static final String FAILED = "FAILED";
	private static final String SUCCESS = "SUCCESS";
	
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
