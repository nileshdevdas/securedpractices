package com.vinsys.securitylabs.leastpriviledge.controller;


import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.captchasolution.CaptchaResponse;
import com.vinsys.securitylabs.captchasolution.CaptchaService;
import com.vinsys.securitylabs.impropervalidation.IImpopervalidation;
import com.vinsys.securitylabs.impropervalidation.vo.CandidateResponseVO;
import com.vinsys.securitylabs.impropervalidation.vo.CreateCandidateRequestVO;
import com.vinsys.securitylabs.leastpriviledge.entity.ERole;
import com.vinsys.securitylabs.leastpriviledge.entity.Role;
import com.vinsys.securitylabs.leastpriviledge.entity.User;
import com.vinsys.securitylabs.leastpriviledge.jwt.AuthTokenFilter;
import com.vinsys.securitylabs.leastpriviledge.jwt.JwtUtils;
import com.vinsys.securitylabs.leastpriviledge.repo.RoleRepository;
import com.vinsys.securitylabs.leastpriviledge.repo.UserRepository;
import com.vinsys.securitylabs.leastpriviledge.request.LoginRequest;
import com.vinsys.securitylabs.leastpriviledge.request.SignupRequest;
import com.vinsys.securitylabs.leastpriviledge.response.JwtResponse;
import com.vinsys.securitylabs.leastpriviledge.response.MessageResponse;
import com.vinsys.securitylabs.leastpriviledge.security.UserDetailsImpl;
import com.vinsys.securitylabs.leastpriviledge.util.AuthorityHelper;
import com.vinsys.securitylabs.otpbypass.solution.GenerateOtpService;
import com.vinsys.securitylabs.otpbypass.solution.OtpResponseEntity;
import com.vinsys.securitylabs.reflected.IReflected;
import com.vinsys.securitylabs.reflected.vo.GetAllCandidateResponseVO;
import com.vinsys.securitylabs.resetpassword.ResetPassRequestVO;
import com.vinsys.securitylabs.tokenExpiration.Userdata;
import com.vinsys.securitylabs.tokenExpiration.repo.SignInRepository;
import com.vinsys.securitylabs.utils.ISecurityConstatnt;
import com.vinsys.securitylabs.utils.SecurityHelper;
import com.vinsys.securitylabs.weakpass.SetPasswordService;

import io.jsonwebtoken.Claims;


//@CrossOrigin(origins = "https://sec.vinsysuat.com", maxAge = 3600, allowCredentials="true")

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
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
  
  @Autowired
  AuthTokenFilter filter;
  
  @Autowired
  private SignInRepository repo;
  
  @Autowired
  IImpopervalidation candidateService;
  
  @Autowired
  GenerateOtpService generateOtpService;
  
  @Autowired
  CaptchaService captchaService;
  
  @Autowired
  IReflected reflectedService;
  
  @Autowired
  AuthorityHelper authorityHelper;


    private SetPasswordService setPasswordService;

  
	@Value("${practicelabs.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	/* OTP generated for OTP bypass solution */
	@GetMapping("/getOtp")
	public ResponseEntity<?> getOtp(){
		
	String otpCode= generateOtpService.createOtp();
	System.out.println(otpCode);
	return ResponseEntity.ok(new OtpResponseEntity(otpCode));		
	}
	
	/* Alphanumeric Captcha generated for Multifactor Authntication  */
	@GetMapping("/getCaptcha")
	public ResponseEntity<?> getCaptcha() {

		String captcha = captchaService.generateCaptcha(4);
		System.out.println(captcha);
		
		return ResponseEntity.ok(new CaptchaResponse(captcha));
	}
 

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	  final long PASSWORD_EXPIRATION_TIME
      = 30L * 24L * 60L * 60L * 1000L;
	  
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    	  
    SecurityContextHolder.getContext().setAuthentication(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    
    long currentTime = System.currentTimeMillis();
    
    Date lastChangedTime = userDetails.getPasswordChangedTime();
    
    if(currentTime > (lastChangedTime.getTime()) + PASSWORD_EXPIRATION_TIME) {

   	    String message ="abc";
		 return ResponseEntity.ok(new JwtResponse(message));
 
       } 
//		else if (!this.isLoggedIn(loginRequest)) 
//		{
//			return ResponseEntity.badRequest()
//					.body(new MessageResponse("Error: you are already logged in...kindly logged out first"));
//		}
		 
       else 
       {
    	   
       String jwt = jwtUtils.generateJwtToken(userDetails);
   		
       List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(Collectors.toList());
       
	  Userdata info = new Userdata();
	  info.setUsername(loginRequest.getUsername());
	  info.setPassword(loginRequest.getPassword());
	  info.setIs_active("Y");
	  info.setIsFirstLogin("Y");
	  info.setTokenId(jwt);
	  repo.save(info);
	
	  return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getId(),
			  userDetails.getUsername(), userDetails.getEmail(), roles, userDetails.getIsActive(), userDetails.getIsFirstLogin()) );
	  }
    
}
  
  @PostMapping("/loggedin")
  public boolean isLoggedIn(@RequestBody LoginRequest loginRequest)
  {
	  if (repo.existsByUsername(loginRequest.getUsername())!=null)
	    {
	    	return false;
	    }
	  else {
		  return true;
	  }
  }

  
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	  
	  
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }
    
    if(!setPasswordService.isValidPassword(signUpRequest.getPassword())) {
    	return ResponseEntity.badRequest().body(new MessageResponse("The password must content At least 8 char Contains at least "
				+ "one digit Contains at least one lower alpha char and one upper alpha char Contains at"
				+ " least one char within a set of special chars (@#%$^ etc.)Does not contain space, tab, etc."));
    }
    
    // Create new user's account
    User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "user":
        	 Role userRole = roleRepository.findByName(ERole.ROLE_USER)
             .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
         roles.add(userRole);
         break;
        default:
          Role userRole1 = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole1);
        }
      });
    }

    user.setPasswordChangedTime(new Date());
    user.setRoles(roles);
    user.setIsActive("Y");
    user.setIsFirstLogin("Y");
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
 
  
  @PostMapping("/logout")
  public String LogoutUser(@RequestBody Userdata info){
	  
	  	// return filter.Logout(info);
	  
	  try {
		List<Userdata> userData=repo.findAllUserByIdAndIsActive(info.getUsername(), "Y");
		for(Userdata user:userData) {
			//repo.updateUser("N",user.getUsername());
			user.setIs_active("N");
			repo.save(user);
		}
		System.out.println("this is logout api");
		return "Logout SuccessFully..!!";
	} catch (Exception e) {
		return e.getMessage();
	}
  }

  @PostMapping("/logout/problem")
  public String logout() {
	  return "Logged Out Successfully";
  }
  	
	
	
	@PostMapping("/create-candidate")
	public CandidateResponseVO createCandidate(@RequestBody CreateCandidateRequestVO candidate) {
		
		return candidateService.createCandidate(candidate);
	}



	
	@GetMapping(value = "/getAllCaandidate")
	public ResponseEntity<GetAllCandidateResponseVO> getAllOrganization(){
		GetAllCandidateResponseVO getAllOrgResponse = null;
		try {
			 getAllOrgResponse=reflectedService.getallCandidate();
			return new ResponseEntity<>(getAllOrgResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(getAllOrgResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
	

	  
	@RequestMapping(path = "/changePassword", method = {
			RequestMethod.PUT }, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String changePassword(@RequestBody ResetPassRequestVO request, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws IOException {
		try {
			if (!SecurityHelper.isNullOrEmpty(request.getUsername())
					&& !SecurityHelper.isNullOrEmpty(request.getPassword())
					&& !SecurityHelper.isNullOrEmpty(request.getNewPassword())) {

				// FIRST TIME LOGIN IS NOT IMPLEMENTED YET
				User entity = userRepository.findByCustomQuery(request.getUsername());


				if (!SecurityHelper.isNullOrEmpty(entity)) {
					String token = httpRequest.getHeader("Authorization");
					System.out.println(token);
					token = token.substring(7, token.length());
					Claims claims = authorityHelper.getLoggedInUserClaims(httpRequest);

					String logedInUser = claims.get("username").toString();


					if (!SecurityHelper.isNullOrEmpty(logedInUser) && logedInUser.equals(request.getUsername())) {

						if (entity != null && !entity.getIsActive().equalsIgnoreCase(ISecurityConstatnt.isNotActive)) {

							if (request != null && !SecurityHelper.isNullOrEmpty(request.getNewPassword())) {

								entity.setPassword(encoder.encode(request.getNewPassword()));
								entity.setIsFirstLogin("N");

								userRepository.save(entity);

								return SecurityHelper.sendSuccessResponse(
										ISecurityConstatnt.PasswordChangeSuccesfullyMessage,
										ISecurityConstatnt.successCode);
							}
						}
					} else {
						throw new com.vinsys.securitylabs.exception.SecurityException(ISecurityConstatnt.INVALID_USER,
								ISecurityConstatnt.INVALID_USER_LOGIN);
					}
				} else {
					throw new com.vinsys.securitylabs.exception.SecurityException(
							ISecurityConstatnt.AuthenticationFailedCode,
							ISecurityConstatnt.AuthenticationFailedMessage);
				}

			} else {
				throw new com.vinsys.securitylabs.exception.SecurityException(ISecurityConstatnt.InsufficientDataCode,
						ISecurityConstatnt.InsufficientDataMessage);
			}
		} catch (SecurityException hre) {
			hre.printStackTrace();
			return SecurityHelper.sendErrorResponse("ER", 22);
		} catch (Exception e) {
			e.printStackTrace();
			return SecurityHelper.sendErrorResponse(ISecurityConstatnt.UnknowErrorMessage,
					ISecurityConstatnt.UnknowErrorCode);
		}
		return null;
	}

	
	
	
}
