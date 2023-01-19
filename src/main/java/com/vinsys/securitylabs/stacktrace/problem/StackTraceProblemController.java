package com.vinsys.securitylabs.stacktrace.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value ="stacktrace")
@RequestMapping(path="/stacktrace/problem")
public class StackTraceProblemController {
	@Autowired
	private StackTraceProblemService stackTraceProblem;
	
	@GetMapping(path="/getstack")
	public void getStack() {
		stackTraceProblem.StackTraceProblem();
	}
	
	
	

}
