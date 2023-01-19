package com.vinsys.securitylabs.avoiddataduplication.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.avoiddataduplication.IUserExists;
import com.vinsys.securitylabs.avoiddataduplication.VO.UserExistsSolutionResponseVO;
import com.vinsys.securitylabs.avoiddataduplication.VO.addUserRequestVO;

@RestController(value="usercheckingsolution")
@RequestMapping(path="/userchecking/solution")
public class UserCheckingSolutionController {

	@Autowired
	IUserExists userxistsrespo;
	
	@PostMapping("/adduser")
	public UserExistsSolutionResponseVO addUser(@RequestBody addUserRequestVO user) {
		return userxistsrespo.addUserSolution(user);
	}
}
