package com.vinsys.securitylabs.leastpriviledge.jwt.exception;

import org.springframework.security.core.AuthenticationException;

import com.vinsys.securitylabs.leastpriviledge.request.BaseResponse;

public class CustomAuthenticationException extends AuthenticationException {

	BaseResponse response;

	public CustomAuthenticationException(BaseResponse response) {
		super(response.getResponseMessage());
		this.response = response;
	}

	public BaseResponse getResponse() {
		return response;
	}

	public void setResponse(BaseResponse response) {
		this.response = response;
	}

	public CustomAuthenticationException(String msg) {
		super(msg);
	}

}

