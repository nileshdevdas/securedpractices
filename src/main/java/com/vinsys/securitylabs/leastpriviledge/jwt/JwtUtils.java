package com.vinsys.securitylabs.leastpriviledge.jwt;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.vinsys.securitylabs.leastpriviledge.security.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${practicelabs.app.jwtSecret}")
	private String jwtSecret;

	@Value("${practicelabs.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(UserDetailsImpl userPrincipal) {
		return generateTokenFromUsername(userPrincipal);
	}

	public String generateTokenFromUsername(UserDetailsImpl userPrincipal) {
		List<String> roles = userPrincipal.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		StringBuilder role = new StringBuilder();
		
		for(String rol:roles) {
			role.append(rol);
			role.append(",");
		}
		
		String userRoles=removeColon(role);
		
		return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date())
				.claim("role", userRoles)
				.claim("username", userPrincipal.getUsername())
				.claim("email", userPrincipal.getEmail())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
	
	private String removeColon(StringBuilder role) {

		int start = role.lastIndexOf(",");
		if (start != -1) {
			int end = start + 1;
			role.delete(start, end);
		}
		return role.toString();
	}

}
