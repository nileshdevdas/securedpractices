package com.vinsys.securitylabs.forcebrowsing;

public class BaseResponseVO {

	private EActionStatus status;
	private String message;
	
	public BaseResponseVO() {
	}
	public BaseResponseVO(EActionStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public EActionStatus getStatus() {
		return status;
	}

	public void setStatus(EActionStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	


}
