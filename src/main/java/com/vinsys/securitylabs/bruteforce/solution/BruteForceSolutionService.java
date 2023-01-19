package com.vinsys.securitylabs.bruteforce.solution;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.vinsys.securitylabs.bruteforce.BruteLoginRequest;
import com.vinsys.securitylabs.bruteforce.BruteLoginResponse;
import com.vinsys.securitylabs.bruteforce.BruteUsers;
import com.vinsys.securitylabs.injection.UserExistResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class BruteForceSolutionService {
	private static final String key = "ABCDEFGHNILESHDEVDAS!@#@#@#@#@#ABCDEFGHNILESHDEVDAS!@#@#@#@#@#ABCDEFGHNILESHDEVDAS!@#@#@#@#@#";
	@Autowired
	private DataSource dataSource;

	private static final String FAILED = "FAILED";
	private static final String SUCCESS = "SUCCESS";
	@Autowired
	private IBruteForceSolutionRepository bruteForceSolutionRepository;

	public static final int MAX_FAILED_ATTEMPTS = 3;

	private static final long LOCK_TIME_DURATION = 3 * 60 * 1000; // 3 minutes

	public BruteLoginResponse login(BruteLoginRequest request) {

		BruteLoginResponse response = new BruteLoginResponse();

		BruteUsers user = bruteForceSolutionRepository.findByEmail(request.getEmail());

		if (user != null) {
			BruteUsers user1 =bruteForceSolutionRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
			if(user.isAccountNonLocked()&&user1==null) {
				if(user.getFailedAttempt()<MAX_FAILED_ATTEMPTS+1) {
					increaseFailedAttempts(user);
					response.setStatuss("Incorrect username or password");
				}else {
					lock(user);
					response.setStatuss("Your account has been locked due to 4 failed attempts. It will be unlocked after 3 min.");
				}
				
			}else if(!user.isAccountNonLocked()) {
				if(unlockWhenTimeExpired(user)) {
					response.setStatuss("Your account has been unlocked.please try to login again.");
				}else {
					response.setStatuss("Your account has been locked due to 4 failed attempts.");
				}
			}else {
				Calendar cl = Calendar.getInstance();
				cl.add(Calendar.MINUTE, 30);
				String token = Jwts.builder().claim("email", request.getEmail()).setSubject(request.getEmail())
						.setExpiration(cl.getTime()).signWith(SignatureAlgorithm.HS512, key.getBytes()).compact();
				response.setJwtTokennnn(token);
				response.setStatuss(token);
				response.setEmail(request.getEmail());
			}
			
			
		} else {
			response.setStatuss("Incorrect username or password");
		}
		return response;
	}

	public boolean unlockWhenTimeExpired(BruteUsers user) {
		long lockTimeInMillis = user.getLockTime().getTime();
		long currentTimeInMillis = System.currentTimeMillis();

		if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
			user.setAccountNonLocked(true);
			user.setLockTime(null);
			user.setFailedAttempt(0);

			bruteForceSolutionRepository.save(user);

			return true;
		}

		return false;
	}

	public void lock(BruteUsers user) {
		user.setAccountNonLocked(false);
        user.setLockTime(new Date());
        
		bruteForceSolutionRepository.save(user);

	}
	
	public void increaseFailedAttempts(BruteUsers user) {
        int newFailAttempts = user.getFailedAttempt() + 1;
        user.setFailedAttempt(newFailAttempts);
        bruteForceSolutionRepository.save(user);
    }
     
    public void resetFailedAttempts(String email) {
    	bruteForceSolutionRepository.updateFailedAttempts(0, email);
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
