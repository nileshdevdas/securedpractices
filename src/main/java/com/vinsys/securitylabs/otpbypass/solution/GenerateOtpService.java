package com.vinsys.securitylabs.otpbypass.solution;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.stereotype.Service;
@Service
public class GenerateOtpService {
	public String createOtp() {
		 
		 String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));
	
	return otp;	
}
}
