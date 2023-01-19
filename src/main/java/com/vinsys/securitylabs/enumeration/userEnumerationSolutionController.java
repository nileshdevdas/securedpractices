package com.vinsys.securitylabs.enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.injection.LoginRequest;

@RestController(value = "userenumeration1")
@RequestMapping(path = "/userenumeration/solution")
public class userEnumerationSolutionController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private EnumerationService service;
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		
		if(!service.existByEmail(request.getEmail())){
        	
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        }
        if(!service.existByPassword(request.getPassword())){
        	
            return new ResponseEntity<>("password does not exist", HttpStatus.BAD_REQUEST);
        }
		
		 return new ResponseEntity<>("User login successfully", HttpStatus.OK);
		
	}
	


}
