package com.vinsys.securitylabs.impropervalidation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.securitylabs.forcebrowsing.Constants;
import com.vinsys.securitylabs.forcebrowsing.EActionStatus;
import com.vinsys.securitylabs.impropervalidation.Candidate;

import com.vinsys.securitylabs.impropervalidation.CandidateHelper;
import com.vinsys.securitylabs.impropervalidation.ICandidateDAO;
import com.vinsys.securitylabs.impropervalidation.IImpopervalidation;
import com.vinsys.securitylabs.impropervalidation.vo.CandidateResponseVO;
import com.vinsys.securitylabs.impropervalidation.vo.CandidateSolutionResponseVO;
import com.vinsys.securitylabs.impropervalidation.vo.CreateCandidateRequestVO;

@Service
public class CandidateService implements IImpopervalidation{
	
	
	@Autowired
	ICandidateDAO candidateRepository;

	@Override
	public CandidateResponseVO createCandidate(CreateCandidateRequestVO candidate) {
		
		try {
			if (Boolean.TRUE.equals(candidateRepository.existsByEmailId(candidate.getEmailId()))) {
				throw new IllegalArgumentException(Constants.USER_ALREADY_EXISTS);
			}
			Candidate createCandidate=new Candidate();
			createCandidate.setFirstName(candidate.getFirstName());
			createCandidate.setLastName(candidate.getLastName());
			createCandidate.setMobileNumber(candidate.getMobileNumber());
			createCandidate.setEmailId(candidate.getEmailId());
			candidateRepository.save(createCandidate);
				
		}catch(IllegalArgumentException iae){
			return new CandidateResponseVO(EActionStatus.FAILURE, iae.getMessage());
		}
		
		return new CandidateResponseVO(EActionStatus.SUCCESS, Constants.USER_CREATE_SUCCES);
	}

	
	
	
	@Override
	public CandidateSolutionResponseVO createSolutionCandidate(CreateCandidateRequestVO candidate)  {
		try {
			Candidate emailCheck = null;
			if (candidate != null && !CandidateHelper.isNullOrEmpty(candidate.getEmailId())) {
				emailCheck = candidateRepository.findByemailId(candidate.getEmailId());
				if (emailCheck == null) {
			         /**************added server side validation************************/
					
								if(!CandidateHelper.isNullOrEmpty(candidate.getFirstName()) && !CandidateHelper.isNullOrEmpty(candidate.getLastName())&&
										!CandidateHelper.isLongZero(candidate.getMobileNumber())&& !CandidateHelper.isNullOrEmpty(candidate.getEmailId())
										) {
									boolean checkFirstName=CandidateHelper.regexMatcher(candidate.getFirstName(), "[a-zA-Z]{2,5}+");
									boolean checkLastName=CandidateHelper.regexMatcher(candidate.getLastName(), "[a-zA-Z]{2,6}+");
									boolean checkMobileNo=CandidateHelper.regexMatcher(Long.toString(candidate.getMobileNumber()), "^(\\d{10}|\\d{12})$");
									boolean checkEmail=CandidateHelper.regexMatcher(candidate.getEmailId(), "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
									
									if(checkFirstName && checkLastName && checkEmail && checkMobileNo) {
										Candidate createCandidate=new Candidate();
										createCandidate.setFirstName(candidate.getFirstName());
										createCandidate.setLastName(candidate.getLastName());
										createCandidate.setMobileNumber(candidate.getMobileNumber());
										createCandidate.setEmailId(candidate.getEmailId());
										candidateRepository.save(createCandidate);
									}else {
										
										 if(!checkFirstName)
											
											 throw new IllegalArgumentException(Constants.FIRSTNAME_ALREADY_EXISTS);
										
										 
										 if(!checkLastName)
											
											 throw new IllegalArgumentException(Constants.LASTNAME_ALREADY_EXISTS);
										
										
										if(!checkMobileNo)
										
											throw new IllegalArgumentException(Constants.MOBILE_ALREADY_EXISTS);
									
										if(!checkEmail)
											
											throw new IllegalArgumentException(Constants.EMAIL_ALREADY_EXISTS);
									}
								}else {
									
									
									throw new IllegalArgumentException(Constants.InsufficientDataMessage);
								}
					
				}else {
						
					throw new IllegalArgumentException(Constants.USER_ALREADY_EXISTS);
				}
				}else {
				
					throw new IllegalArgumentException(Constants.InsufficientDataMessage);
			}
			
		}catch(IllegalArgumentException iae){
			return new CandidateSolutionResponseVO(EActionStatus.FAILURE, iae.getMessage());
		}
		return new CandidateSolutionResponseVO(EActionStatus.SUCCESS, Constants.USER_CREATE_SUCCES);
	}
	
	
	
}

