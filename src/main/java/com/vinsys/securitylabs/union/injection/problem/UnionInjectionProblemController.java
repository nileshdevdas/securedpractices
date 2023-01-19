package com.vinsys.securitylabs.union.injection.problem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.injection.UserExistResponse;

@RestController(value = "unioninjectionproblem")
@RequestMapping(path = "union/injection/problem")
public class UnionInjectionProblemController {
	@Autowired
	private UnionInjectionProblemService unionInjectionProblem;
	
	@GetMapping(path = "/finduser")
	public UserExistResponse checkUser(@RequestParam String request) {
		return unionInjectionProblem.checkUser(request);
	}
}
