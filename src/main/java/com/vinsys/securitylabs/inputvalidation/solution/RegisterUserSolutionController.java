package com.vinsys.securitylabs.inputvalidation.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.inputvalid.RegisterUser;
import com.vinsys.securitylabs.inputvalid.RegisterValidUserService;


@RestController(value="inputinvalidsolution")
@RequestMapping(path="/inputinvalid/solution")
public class RegisterUserSolutionController {
	
	@Autowired
	private RegisterValidUserService service;
		
	@PostMapping(path="/checkuser")
	public boolean checkuser(@RequestBody RegisterUser user) {
		return service.isvalidInput(user.getName(), user.getEmail_id(), user.getContact_no());
	}
	
	@PostMapping(path="/adduser")
	public String addUser(@RequestBody RegisterUser request) {
		return service.RegisteredUser(request);
	}

}
