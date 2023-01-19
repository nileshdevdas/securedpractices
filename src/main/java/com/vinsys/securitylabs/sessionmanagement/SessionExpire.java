package com.vinsys.securitylabs.sessionmanagement;

import java.sql.Time;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//@Configuration

public class SessionExpire{

//	@Value("${server.servlet.session.timeout}")
//	private Integer sessionTimeout;
//	
//
//	public void sessonCreated(HttpSessionEvent event)
//	{
//	event.getSession().setMaxInactiveInterval(sessionTimeout);	
//	}
	
	public String sessionExpired()
	{
		long start=System.currentTimeMillis();
		long end=start + 30 * 1000;
		while (System.currentTimeMillis()<end) {
			
		}
		return "Session Expired";
		
	}
	
}
