package com.vinsys.securitylabs.reflected.vo;

import java.util.List;

import com.vinsys.securitylabs.forcebrowsing.BaseResponseVO;
import com.vinsys.securitylabs.forcebrowsing.EActionStatus;
import com.vinsys.securitylabs.impropervalidation.Candidate;

public class GetAllCandidateResponseVO extends BaseResponseVO {
	
	List<Candidate> listResponse;
	
//	public List<Candidate> getCandList(){
//		return candList;
//	}
	

	public GetAllCandidateResponseVO(EActionStatus status, String message,List<Candidate> listResponse) {
		super(status, message);
		this.listResponse = listResponse;
	}
	
	public List<Candidate> getListResponse() {
		return listResponse;
	}

	public void setListResponse(List<Candidate> listResponse) {
		this.listResponse = listResponse;
	}

	public GetAllCandidateResponseVO(EActionStatus status, String message) {
		super(status, message);
		
	}

	public GetAllCandidateResponseVO(List<Candidate> listResponse) {
	
		this.listResponse = listResponse;
	}

	
	

}
