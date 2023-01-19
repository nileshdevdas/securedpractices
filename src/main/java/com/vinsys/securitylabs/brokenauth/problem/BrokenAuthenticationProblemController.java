package com.vinsys.securitylabs.brokenauth.problem;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.brokenauth.ProfileService;
import com.vinsys.securitylabs.brokenauth.User;

@RestController(value = "brokenauthproblem")
@RequestMapping(path = "/brokenauth/problem")
public class BrokenAuthenticationProblemController {
	@Autowired
	private ProfileService service;

	@GetMapping(path = "profile")
	public User getProfile(@RequestParam("id") Long id) {
		return service.getProfile(id);
	}

	@GetMapping(path = "file")
	public void getPicture(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String picName = request.getParameter("name");
		System.out.println("file Name " + picName);
		Resource resource = new FileSystemResource(picName);
		System.out.println(resource.getURL());
		InputStream input = resource.getInputStream();
		byte b[] = new byte[input.available()];
		input.read(b);
		response.getOutputStream().write(b);
	}
}
