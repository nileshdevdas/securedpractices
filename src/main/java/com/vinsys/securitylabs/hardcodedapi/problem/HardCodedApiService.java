package com.vinsys.securitylabs.hardcodedapi.problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.securitylabs.cleartextdatatransmission.DataEntity;

@Service
public class HardCodedApiService {

	@Autowired
	private DataSource datasource;
	
	DataEntity de=null;
	Connection con= null;
	String message=null;
	public DataEntity registerUser(String passcode ,String username) {
		
	
		try {
			con=datasource.getConnection();
			String sql="insert into tbl_confidential_data(passcode,username)values(?,?)";
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setString(1, "indrani123@");
			ps.setString(2, "indrani");
			ps.execute();
			message="Data Added using Hardcode!";
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				try{
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return de;
		
	}
	
	
	
	
}
