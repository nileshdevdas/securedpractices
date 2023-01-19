package com.vinsys.securitylabs.hardcodedapi.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.cleartextdatatransmission.DataEntity;

@RestController(value="hardcodedapi")
@RequestMapping(path="/hardcodedapi/problem")
public class HardCodedApiController {
	@Autowired
	private HardCodedApiService hcas;
	
	@PostMapping(path="/reg/user")
	public DataEntity Register(@RequestBody DataEntity request) {
		return hcas.registerUser(request.getPasscode(), request.getUsername());
	}
	
	

}
