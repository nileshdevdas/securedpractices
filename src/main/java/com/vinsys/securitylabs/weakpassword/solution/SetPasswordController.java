package com.vinsys.securitylabs.weakpassword.solution;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.leastpriviledge.entity.User;
import com.vinsys.securitylabs.leastpriviledge.request.SignupRequest;
import com.vinsys.securitylabs.weakpass.SetPasswordService;

@RestController(value="weakpasswordsolution")
@RequestMapping(path="/weakpassword/solution")
public class SetPasswordController {
	@Autowired
	private SetPasswordService loginServiceJDBC;	
	
	@PostMapping(path ="/checkpass")
	public Boolean CheckPass(@RequestBody User request)
	{
		return loginServiceJDBC.isValidPassword(request.getPassword());
	}
	

	@PutMapping(path="/changepass")
	public String changePass(@RequestBody SignupRequest request)
	{
		return loginServiceJDBC.changePassword(request);
	}
}
