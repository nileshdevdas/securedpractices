package com.vinsys.securitylabs.leastpriviledge.util;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vinsys.securitylabs.leastpriviledge.entity.ERole;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class AuthorityHelper {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${practicelabs.app.jwtSecret}")
	private String jwtSecret;
	
	public Claims getLoggedInUserClaims(HttpServletRequest httpServletRequest) {
		String authToken = httpServletRequest.getHeader("Authorization");
		String token = authToken.substring(7, authToken.length());
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.getMessage();
		}
		return claims;
		
	}
	
	
	public boolean getRolePresent(HttpServletRequest httpServletRequest,String role) {
		boolean roleFlag=false;
		Claims claims=getLoggedInUserClaims(httpServletRequest);
		String roles=claims.get("role").toString();
		String[] rol=roles.split(",");
		
		for(String r:rol) {
			if(r.equals(role)) {
				roleFlag=true;
			}
		}
		
		boolean contains = Arrays.stream(rol).anyMatch(role::equals);

		
		return contains;
	}
	
}
