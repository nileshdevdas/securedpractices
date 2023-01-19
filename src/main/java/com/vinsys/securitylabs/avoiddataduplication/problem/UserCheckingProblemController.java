package com.vinsys.securitylabs.avoiddataduplication.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.avoiddataduplication.IUserExists;
import com.vinsys.securitylabs.avoiddataduplication.VO.UserExistsResponseVO;
import com.vinsys.securitylabs.avoiddataduplication.VO.addUserRequestVO;

@RestController(value="usercheckingproblem")
@RequestMapping(path="/usercheckingproblem/problem")
public class UserCheckingProblemController {
	
	@Autowired
	IUserExists userexistsservice;
	
	@PostMapping("/adduser")
	public UserExistsResponseVO addUser(@RequestBody addUserRequestVO user) {
		return userexistsservice.addUser(user);
	}
	

}
