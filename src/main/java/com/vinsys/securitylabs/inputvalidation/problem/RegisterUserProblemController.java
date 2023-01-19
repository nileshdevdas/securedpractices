package com.vinsys.securitylabs.inputvalidation.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.inputvalid.RegisterUser;
import com.vinsys.securitylabs.inputvalid.registerUserService;

@RestController(value="inputinvalidproblem")
@RequestMapping(path="/inputinvalid/problem")
public class RegisterUserProblemController {

	
	@Autowired
	private registerUserService regservice;
	
	@PostMapping(path="/registeruser")
	public RegisterUser Register(@RequestBody RegisterUser user) {
		return regservice.Register(user.getName(), user.getEmail_id(), user.getContact_no());
	}
	
}
