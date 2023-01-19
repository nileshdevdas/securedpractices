package com.vinsys.securitylabs.apiaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.injection.LoginRequest;
import com.vinsys.securitylabs.injection.LoginResponse;
import com.vinsys.securitylabs.injection.LoginServiceJDBC;
import com.vinsys.securitylabs.injection.UserExistRequest;
import com.vinsys.securitylabs.injection.UserExistResponse;

@RestController(value = "apiaccess")
@RequestMapping(value="/api", method={RequestMethod.POST , RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE})
public class ApiAccessProblemController {
	
	
	@Autowired
	private LoginServiceJDBC loginServiceJDBC;
	

	@PostMapping(path = "/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		return loginServiceJDBC.login(request);
	}

	@PostMapping(path = "/finduser")
	public UserExistResponse checkUser(@RequestBody UserExistRequest request) {
		return loginServiceJDBC.checkUser(request.getEmail());
	}
	

}
