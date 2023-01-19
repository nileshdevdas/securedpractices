package com.vinsys.securitylabs.leastpriviledge.jwt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vinsys.securitylabs.leastpriviledge.entity.User;
import com.vinsys.securitylabs.leastpriviledge.jwt.exception.CustomAuthenticationException;
import com.vinsys.securitylabs.leastpriviledge.repo.UserRepository;
import com.vinsys.securitylabs.leastpriviledge.security.UserDetailsServiceImpl;
import com.vinsys.securitylabs.tokenExpiration.Userdata;
import com.vinsys.securitylabs.tokenExpiration.repo.SignInRepository;


public class AuthTokenFilter extends OncePerRequestFilter {
	  @Autowired
	  private JwtUtils jwtUtils;

	  @Autowired
	  private UserDetailsServiceImpl userDetailsService;
	  
	  @Autowired
	  private SignInRepository repo;
	  
	  @Autowired
	  private DataSource datasource;
	  

	  String message;
	  @Autowired
	  private SignInRepository signinRepo;
	 

	  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	  @Override
	  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	      throws ServletException, IOException {
	    try {
	      String jwt = parseJwt(request);
	      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
	        String username = jwtUtils.getUserNameFromJwtToken(jwt);

	        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	        userLoginStatus(username,jwt);
	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
	            userDetails.getAuthorities());
	        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	      }
	    } catch (Exception e) {
	      logger.error("Cannot set user authentication: {}", e.getMessage());
	    }

	    filterChain.doFilter(request, response);
	  }

	  private void userLoginStatus(String username, String jwt) {
		  Userdata userData=repo.findByUsernameAndIsActiveAndTokenId(username, "Y", jwt);
		  if(userData==null) {
			  throw new IllegalArgumentException("unauthorized");
		  }else {
			  System.out.println("valid user");
		  }
	}

	private String parseJwt(HttpServletRequest request) {
	    String headerAuth = request.getHeader("Authorization");

	    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
	      return headerAuth.substring(7, headerAuth.length());
	    }

	    return null;
	  }

	  public String Logout(@RequestBody Userdata info) {
		 

		  Connection con=null;
		  
		  try {
			  con=datasource.getConnection();
			  String sql ="update tbl_user_activity set is_active=? where username=?";
			  PreparedStatement stmt = con.prepareStatement(sql);
			  stmt.setString(1, info.getIs_active());
			  stmt.setString(2,info.getUsername());
			  stmt.execute();
			 message="You have succesfully logged out";
		  }catch(Exception e) {
			  e.printStackTrace();
		  }finally {
			  if(con!=null) {
				  try {
					  con.close();
				  }catch(SQLException e) {
					  e.printStackTrace();
				  }
			  }
		  }
		  
		  return message;
	  }
}
