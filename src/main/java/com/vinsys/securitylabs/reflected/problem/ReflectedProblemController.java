package com.vinsys.securitylabs.reflected.problem;


import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.reflected.IReflected;
import com.vinsys.securitylabs.reflected.vo.GetAllCandidateResponseVO;

@RestController(value = "reflectedproblem")
@RequestMapping(path = "/reflectedproblem/problem")
public class ReflectedProblemController {
	
	@Autowired
	IReflected reflectedService;

	
	@GetMapping(value = "/getAllCaandidate")
	public ResponseEntity<GetAllCandidateResponseVO> getAllOrganization(){
		GetAllCandidateResponseVO getAllOrgResponse = null;
		try {
			 getAllOrgResponse=reflectedService.getallCandidate();
			return new ResponseEntity<>(getAllOrgResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(getAllOrgResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
}
