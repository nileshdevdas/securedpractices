package com.vinsys.securitylabs.impropervalidation.vo;

import com.vinsys.securitylabs.forcebrowsing.BaseResponseVO;
import com.vinsys.securitylabs.forcebrowsing.EActionStatus;


public class CandidateResponseVO extends BaseResponseVO {

	public CandidateResponseVO() {
		
	}

	public CandidateResponseVO(EActionStatus status, String message) {
		super(status, message);
		
	}

	
	
}
