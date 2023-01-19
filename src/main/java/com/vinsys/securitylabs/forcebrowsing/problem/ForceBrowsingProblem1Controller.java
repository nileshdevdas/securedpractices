package com.vinsys.securitylabs.forcebrowsing.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.forcebrowsing.GetAllRoleResponseVO;
import com.vinsys.securitylabs.forcebrowsing.GetAllRoleResponseVO1;
import com.vinsys.securitylabs.forcebrowsing.IForceBrowsingProblem;
import com.vinsys.securitylabs.forcebrowsing.IForceBrowsingProblem1;

@RestController(value = "forcebrowserproblem1")
@RequestMapping(path = "/forcebrowser/problem1")
public class ForceBrowsingProblem1Controller {

	
	@Autowired
	IForceBrowsingProblem1 forceservice;
	
	
	@GetMapping("/getAllRole/{id}")
	public GetAllRoleResponseVO1 getOrgByUser(@PathVariable("id") Long Id){ 
   return forceservice.viewRoleList1(Id);
		
}


}
