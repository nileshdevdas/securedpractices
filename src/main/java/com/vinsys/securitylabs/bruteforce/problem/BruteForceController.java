package com.vinsys.securitylabs.bruteforce.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.bruteforce.BruteLoginRequest;
import com.vinsys.securitylabs.bruteforce.BruteLoginResponse;
import com.vinsys.securitylabs.bruteforce.BruteForceLoginService;
import com.vinsys.securitylabs.injection.LoginRequest;


@RestController(value = "bruteforceproblem")
@RequestMapping(path = "/bruteforce/problem")
public class BruteForceController {
	@Autowired
	private BruteForceLoginService loginService;
	
	@PostMapping(path = "/login")
	public BruteLoginResponse login(@RequestBody BruteLoginRequest request) {
		return loginService.login(request);
	}
	

}
