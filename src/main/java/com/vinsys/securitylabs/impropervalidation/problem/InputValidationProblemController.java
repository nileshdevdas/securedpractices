package com.vinsys.securitylabs.impropervalidation.problem;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.impropervalidation.IImpopervalidation;
import com.vinsys.securitylabs.impropervalidation.vo.CandidateResponseVO;
import com.vinsys.securitylabs.impropervalidation.vo.CreateCandidateRequestVO;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController(value = "inputvalidationproblem")
@RequestMapping(path = "/inputvalidationproblem/problem")
public class InputValidationProblemController {
	
	@Autowired
	IImpopervalidation candidateService;
	
	@PostMapping("/create-candidate")
	public CandidateResponseVO createCandidate(@RequestBody CreateCandidateRequestVO candidate) {
		
		return candidateService.createCandidate(candidate);
	}

}
