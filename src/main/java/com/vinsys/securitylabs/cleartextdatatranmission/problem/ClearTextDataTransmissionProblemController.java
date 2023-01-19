package com.vinsys.securitylabs.cleartextdatatranmission.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.cleartextdatatransmission.DataEntity;

@RestController(value ="cleartextproblem")
@RequestMapping(path="/cleartextdata/problem")
public class ClearTextDataTransmissionProblemController {
	
	@Autowired
	ClearTextDataTransmissionService clearTextDataTransmission;
	
	
	@PostMapping("/addtext")
	public DataEntity registerUser(@RequestBody DataEntity request)
	{
		return clearTextDataTransmission.registerUser(request.getUsername(), request.getPasscode());
		
	}
	
	@GetMapping("/confirmregistration")
	public String ConfirmReg() {
		return "confirm";
	}
	
}
