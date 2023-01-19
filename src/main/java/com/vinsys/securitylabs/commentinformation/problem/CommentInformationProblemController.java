package com.vinsys.securitylabs.commentinformation.problem;

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


@RestController(value="commentinformationproblem")
@RequestMapping(path="/commentinformation/problem")
public class CommentInformationProblemController {
	
	@Autowired
	private ViewUserDAO viewuser;
	
	@GetMapping("/view/{id}") // getting information for specified id like id = 1
	public Optional<UserInfo> getInfobyid(@PathVariable(value="id") Long id) {
		return viewuser.findById(id); // {getting information like : "id": 6, "name": "sakshi", "contactNo": "9812938912","emailId": "sakshi@gmail.com"}
	}
	
	@GetMapping("/view") //getting information of all users 
	public List<UserInfo> getAll(){
		return viewuser.findAll();// getting information like :[{ "id": 6, "name": "sakshi", "contactNo": "9812938912","emailId": "sakshi@gmail.com"}]
	}

}
