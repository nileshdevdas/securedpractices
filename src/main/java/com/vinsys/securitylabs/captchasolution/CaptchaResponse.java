package com.vinsys.securitylabs.captchasolution;

public class CaptchaResponse {
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CaptchaResponse(String code) {
		
		this.code = code;
	}

}
