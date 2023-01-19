package com.vinsys.securitylabs.enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.enumeration.EnumerationService;
import com.vinsys.securitylabs.injection.LoginRequest;

@RestController(value = "userenumeration")
@RequestMapping(path = "/userenumeration/problem")
public class userEnumerationProblemController {
	
	@Autowired
	private EnumerationService service;

	@Autowired
	private LoginService loginService;
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		
		if(!service.existByEmail(request.getEmail())){
        	
            return new ResponseEntity<>("username does not exist", HttpStatus.BAD_REQUEST);
        }
        if(!service.existByPassword(request.getPassword())){
        	
            return new ResponseEntity<>("password does not exist", HttpStatus.BAD_REQUEST);
        }
		
		 return new ResponseEntity<>("User login successfully", HttpStatus.OK);
		
	}
	


	
	
	
	
	

//	@Autowired
//	private EnumerationService service;
//	
//	@PostMapping("/signin")
//    public ResponseEntity<?> registerUser(@RequestBody User user){
//
//		// add check for email exists in DB
//        if(!service.existByEmail(user.getEmail())){
//        	
//            return new ResponseEntity<>("username does not exist", HttpStatus.BAD_REQUEST);
//        }
//        
//
//        return new ResponseEntity<>("User login successfully", HttpStatus.OK);
//
//    }
	
}

