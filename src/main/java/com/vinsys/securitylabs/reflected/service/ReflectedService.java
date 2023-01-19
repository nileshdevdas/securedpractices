package com.vinsys.securitylabs.reflected.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.securitylabs.forcebrowsing.Constants;
import com.vinsys.securitylabs.forcebrowsing.EActionStatus;
import com.vinsys.securitylabs.impropervalidation.Candidate;
import com.vinsys.securitylabs.impropervalidation.ICandidateDAO;
import com.vinsys.securitylabs.reflected.IReflected;
import com.vinsys.securitylabs.reflected.vo.GetAllCandidateResponseVO;


@Service
public class ReflectedService implements IReflected {
	@Autowired
	ICandidateDAO candidateRepository;

	@Override
	public GetAllCandidateResponseVO getallCandidate() {
		try {
			List<Candidate> listResponse=candidateRepository.findAll();
			return new GetAllCandidateResponseVO(EActionStatus.SUCCESS, Constants.ALL_LIST, listResponse);
		}catch(Exception e) {
			return new GetAllCandidateResponseVO(EActionStatus.EXCEPTION, Constants.EXCEPTION) ;
		}	
	}

}
