package com.vinsys.securitylabs.stacktrace.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="stacktracesolution")
@RequestMapping(path="/stacktrace/solution")
public class StackTraceSolutionController {
	@Autowired
	private StackTraceSolutionService stackTraceSolution;
	
	@GetMapping(path="/getstacksolution")
	public String getstackSol() {
		return stackTraceSolution.stackTraceSolution();
	}

}
