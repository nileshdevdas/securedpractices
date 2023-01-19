package com.vinsys.securitylabs.brokenauth.solution;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
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

@RestController(value = "brokenauthsolution")
@RequestMapping(path = "/brokenauth/solution")
public class BrokenAuthenticationSolutionController {
	private List<String> allowedFiles = new ArrayList<>();

	@PostConstruct
	private void allowedResources() {
		Resource resource = new FileSystemResource("d:/temp");
		try {
			 System.out.println(resource.getURL());
			if (resource.getFile().isDirectory()) {
				File res = resource.getFile();
				allowedFiles.addAll(Arrays.asList(res.list(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						if (name.endsWith("png") || name.endsWith("jpg") || name.endsWith("svg"))
							return true;
						else
							return false;
					}
				})));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(allowedFiles);
	}

	@Autowired
	private ProfileService service;

	@GetMapping(path = "profile")
	public User getProfile(@RequestParam("guid") String guid) {
		return service.getProfileByGuid(guid);
	}

	@GetMapping(path = "file")
	public void getPicture(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String picName = request.getParameter("name");
		System.out.println("file Name " + picName);
		if (allowedFiles.contains(picName)) {
			Resource resource = new FileSystemResource("d:/temp/" + picName);
			System.out.println(resource.getURL());
			InputStream input = resource.getInputStream();
			byte b[] = new byte[input.available()];
			input.read(b);
			response.getOutputStream().write(b);
		} else {
			PrintWriter out = response.getWriter();
			out.println("No Such File");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
