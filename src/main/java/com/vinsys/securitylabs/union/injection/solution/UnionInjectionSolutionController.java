package com.vinsys.securitylabs.union.injection.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.injection.UserExistResponse;

@RestController(value = "unioninjectionsolution")
@RequestMapping(path = "union/injection/solution")
public class UnionInjectionSolutionController {

	@Autowired
	private UnionInjectionSolutionService unionInjectionSolution;
	
	@GetMapping(path = "/finduser")
	public UserExistResponse checkUser(@RequestParam String request) {
		return unionInjectionSolution.checkUser(request);
	}
}
