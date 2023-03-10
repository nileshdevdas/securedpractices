package com.vinsys.securitylabs.jwtmanipulation.solution;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.leastpriviledge.jwt.JwtUtils;
import com.vinsys.securitylabs.leastpriviledge.repo.RoleRepository;
import com.vinsys.securitylabs.leastpriviledge.repo.UserRepository;
import com.vinsys.securitylabs.leastpriviledge.request.LoginRequest;
import com.vinsys.securitylabs.leastpriviledge.response.JwtResponse;
import com.vinsys.securitylabs.leastpriviledge.security.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class JwtManipulationSolutionController {

	  @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  UserRepository userRepository;

	  @Autowired
	  RoleRepository roleRepository;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;

	 

	  @PostMapping("/JwtManipulation/solution/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	    Authentication authentication = authenticationManager
	        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);

	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

	    String jwt = jwtUtils.generateJwtToken(userDetails);

	    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
	        .collect(Collectors.toList());


	    return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getId(),
	        userDetails.getUsername(), userDetails.getEmail(), roles, null, null));
	  }
	
}
