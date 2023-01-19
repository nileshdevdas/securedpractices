package com.vinsys.securitylabs.forcebrowsing.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.forcebrowsing.GetAllRoleResponseVO;
import com.vinsys.securitylabs.forcebrowsing.IForceBrowsingProblem;

@RestController(value = "forcebrowsingsolution")
@RequestMapping(path = "/forcebrowser/solution")
public class ForceBrowsingSolutionController {

	
	@Autowired
	IForceBrowsingProblem forceservice;
	
	
	@GetMapping("/getAllRole/{id}")
	public GetAllRoleResponseVO getOrgByUser(@PathVariable("id") Long Id){ 
   return forceservice.viewRoleList(Id);
		
}

}
