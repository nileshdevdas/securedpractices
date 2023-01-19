package com.vinsys.securitylabs.bruteforce.solution;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.vinsys.securitylabs.bruteforce.BruteLoginRequest;
import com.vinsys.securitylabs.bruteforce.BruteLoginResponse;
import com.vinsys.securitylabs.bruteforce.BruteUsers;


																				
@RestController(value = "bruteforcesolution")
@RequestMapping(path = "/bruteforce/solution")
public class BruteForceSolutionController {

	@Autowired
	private BruteForceSolutionService loginService; 

	
	@PostMapping(path = "/login")
	public BruteLoginResponse login(@RequestBody BruteLoginRequest request) {
		return loginService.login(request);
	}



}
