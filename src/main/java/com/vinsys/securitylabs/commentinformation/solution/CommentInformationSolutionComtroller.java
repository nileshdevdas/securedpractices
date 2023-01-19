package com.vinsys.securitylabs.commentinformation.solution;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.commentinformation.UserInfo;
import com.vinsys.securitylabs.commentinformation.ViewUserDAO;


@RestController(value="commentinformationsolution")
@RequestMapping(path="/commentinformation/solution")
public class CommentInformationSolutionComtroller {
	
	
	@Autowired
	private ViewUserDAO viewuser;
	
	@GetMapping("/view/{id}") 
	public Optional<UserInfo> getInfobyid(@PathVariable(value="id") Long id) {
		return viewuser.findById(id); 
	}
	
	@GetMapping("/view") 
	public List<UserInfo> getAll(){
		return viewuser.findAll();
	}

	
	

}
