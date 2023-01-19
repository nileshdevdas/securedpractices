package com.vinsys.securitylabs.stacktrace.solution;

import org.springframework.stereotype.Service;

@Service
public class StackTraceSolutionService {
 private String message;
	public String stackTraceSolution() {
		
		
		try {
			System.out.println(10/0);
			
		}catch(ArithmeticException e)
		{
			return message="Something Went Wrong";
		}
		return message;
		
	}
}
