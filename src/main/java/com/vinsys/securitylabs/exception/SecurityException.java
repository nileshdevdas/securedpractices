package com.vinsys.securitylabs.exception;

public class SecurityException   extends Exception {
	
	

	private static final long serialVersionUID = 1L;
	private String responseMessage ;
	private int responseCode;
	
	
	public SecurityException() {

	}
	
	public SecurityException(String responseMessage) {
		super(responseMessage);
		this.setResponseMessage(responseMessage);
	}
	
	public SecurityException(int responseCode, String responseMessage) {
		super(responseMessage);

		this.setResponseMessage(responseMessage);
		this.responseCode = responseCode;
	}

	
	
	public SecurityException(Throwable cause) {
		super(cause);
	}
	
	public SecurityException(String responseMessage, Throwable cause) {
		super(responseMessage, cause);
		this.setResponseMessage(responseMessage);
	}

	public SecurityException(String responseMessage, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(responseMessage, cause, enableSuppression, writableStackTrace);
		this.setResponseMessage(responseMessage);
	}

	public static Throwable getRootCause(Throwable throwable) {
		if (throwable.getCause() != null)
			return getRootCause(throwable.getCause());

		return throwable;
	}
	
	
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	
	

}
