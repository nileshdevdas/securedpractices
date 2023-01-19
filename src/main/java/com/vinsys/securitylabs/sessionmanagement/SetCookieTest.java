package com.vinsys.securitylabs.sessionmanagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCookieTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/plain");
		res.addHeader("Set-Cookie","SESSIONID=31d4d96e407aad42; Path=/; Secure; HttpOnly");
		res.addHeader("Set-Cookie","lang=en-US; Path=/; Domain=SecurityLabs.com");
        PrintWriter out = res.getWriter();
         out.println("Hello From: " + this.getClass().getName());	
	}

	

}
