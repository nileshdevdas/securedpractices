package com.vinsys.securitylabs.impropervalidation;

import com.vinsys.securitylabs.impropervalidation.vo.CandidateResponseVO;
import com.vinsys.securitylabs.impropervalidation.vo.CandidateSolutionResponseVO;
import com.vinsys.securitylabs.impropervalidation.vo.CreateCandidateRequestVO;

public interface IImpopervalidation {
	
	
	public CandidateResponseVO createCandidate(CreateCandidateRequestVO candidate);
	public CandidateSolutionResponseVO createSolutionCandidate(CreateCandidateRequestVO candidate);

}
