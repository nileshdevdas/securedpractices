package com.vinsys.securitylabs.hardcodedapi.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.cleartextdatatransmission.DataEntity;


@RestController(value="hardcodeapisolution")
@RequestMapping(path="/hardcodeapi/solution")
public class HardCodedApiSolutionController {
	
	@Autowired
	private HardCodedApiServiceSolution hardCodedApiServiceSolution ;
	
	@PostMapping(path="/reg/user/solution")
	public  DataEntity RegUser(@RequestBody DataEntity request)
	{
		return hardCodedApiServiceSolution.registerUserdynamically(request.getPasscode(), request.getUsername());
	}

}
