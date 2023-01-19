package com.vinsys.securitylabs.logout.solution;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/logoutsolution")
public class LogoutSolution {

	
	@GetMapping("/logout")
	public String Logout(HttpServletResponse response) {
		return "logout by clearing token";
	}


}
