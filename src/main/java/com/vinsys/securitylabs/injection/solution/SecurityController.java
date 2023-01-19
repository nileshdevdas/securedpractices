package com.vinsys.securitylabs.injection.solution;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.injection.LoginRequest;
import com.vinsys.securitylabs.injection.LoginResponse;
import com.vinsys.securitylabs.injection.LoginServiceJDBC;
import com.vinsys.securitylabs.injection.UserExistRequest;
import com.vinsys.securitylabs.injection.UserExistResponse;

@RestController(value = "injectionsolution")
@RequestMapping(path = "/injection/solution")
public class SecurityController {
	@Autowired
	private LoginServiceJDBC loginServiceJDBC;

	@PostMapping(path = "/login")
	public LoginResponse login(@Valid @RequestBody LoginRequest request) {
		return loginServiceJDBC.login(request);
	}

	@PostMapping(path = "/finduser")
	public UserExistResponse checkUser(@Valid @RequestBody UserExistRequest request) {
		return loginServiceJDBC.checkUser(request.getEmail());
	}
}
