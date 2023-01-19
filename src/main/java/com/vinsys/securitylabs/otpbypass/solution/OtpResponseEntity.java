package com.vinsys.securitylabs.otpbypass.solution;

public class OtpResponseEntity {
	private String OTP;

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

	public OtpResponseEntity (String oTP) {
		
		OTP = oTP;
	}

}
