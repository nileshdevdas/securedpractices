package com.vinsys.securitylabs.cleartextdatatranmission.problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.securitylabs.brokenauth.User;
import com.vinsys.securitylabs.cleartextdatatransmission.DataEntity;

@Service
public class ClearTextDataTransmissionService {

@Autowired
private DataSource ds;

	public DataEntity registerUser(String username, String passcode) {
		
		DataEntity dE = null;
		Connection con = null;
		try {
			con=ds.getConnection();
			String sql="insert into tbl_confidential_data(username,passcode) values(?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2, passcode);
			ps.execute();
		}catch(Exception e)
		{
			e.printStackTrace();
		} finally {
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return dE;
		
	}
	
}
