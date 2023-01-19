package com.vinsys.securitylabs.leastpriviledge.problem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.leastpriviledge.entity.Role;
import com.vinsys.securitylabs.leastpriviledge.repo.RoleRepository;
import com.vinsys.securitylabs.leastpriviledge.request.BaseResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/problem")
public class UserControllerProblem {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerProblem.class);

	@Value("${practicelabs.app.jwtSecret}")
	private String jwtSecret;
	
	@Autowired
	RoleRepository roleRepository;
	 
	@GetMapping("/getroles")
	public BaseResponse getRole(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		BaseResponse response=new BaseResponse();
		try {
			
			List<Role> roleList=roleRepository.findAll();
			
			response.setResponseBody(roleList);
			response.setResponseCode(101);
			response.setResponseMessage("Get Roles SuccessFully..!!");
			response.setTotalRecords(roleList.size());
		} 
		catch (Exception e) {
			response.setResponseMessage("Exception");
		}
		return response;
	}
	
	
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
	
}

