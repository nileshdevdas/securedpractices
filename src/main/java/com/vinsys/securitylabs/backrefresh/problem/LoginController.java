package com.vinsys.securitylabs.backrefresh.problem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/")
	public String login() {
		return "Login";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome";
	}
	
	@GetMapping("/login")
	public String Login() {
		return "Login";
	}
	
}
