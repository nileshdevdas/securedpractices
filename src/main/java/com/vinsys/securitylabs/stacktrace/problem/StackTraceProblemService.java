package com.vinsys.securitylabs.stacktrace.problem;

import org.springframework.stereotype.Service;

@Service
public class StackTraceProblemService {
	
	public void StackTraceProblem() {
		
		System.out.println(10/0);
		
	}
}
