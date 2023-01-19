package com.vinsys.securitylabs.impropervalidation.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.vinsys.securitylabs.impropervalidation.IImpopervalidation;
import com.vinsys.securitylabs.impropervalidation.vo.CandidateResponseVO;
import com.vinsys.securitylabs.impropervalidation.vo.CandidateSolutionResponseVO;
import com.vinsys.securitylabs.impropervalidation.vo.CreateCandidateRequestVO;


@RestController(value = "inputvalidationsolution")
@RequestMapping(path = "/inputvalidationsolution/solution")
@CrossOrigin(origins = "*")
public class InputValidationSolutionController {

	
	@Autowired
	IImpopervalidation candidateService;
	
	@PostMapping("/create-candidate")
	public CandidateSolutionResponseVO createCandidate(@RequestBody CreateCandidateRequestVO candidate) {
		
		return candidateService.createSolutionCandidate(candidate);
	}



}
