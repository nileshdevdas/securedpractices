package com.vinsys.securitylabs.weakpassword.problem;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.leastpriviledge.entity.User;
import com.vinsys.securitylabs.weakpass.CreateUserService;

@RestController(value="weakpasswordproblem")
@RequestMapping(path="/weakpassword/problem")
public class SetPasswordController {
@Autowired
private CreateUserService loginServiceJDBC;

@PostMapping(path="/createuser")
public User Login(@RequestBody User request) {
	return loginServiceJDBC.createUser(request.getUsername(),request.getEmail(),request.getPassword(),request.getIsActive(),request.getIsFirstLogin(),request.getPasswordChangedTime());
	
}
	
}
