package com.vinsys.securitylabs.impropervalidation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICandidateDAO extends JpaRepository<Candidate, Long> {
	
	Boolean existsByEmailId(String email);
	public Candidate findByemailId(String emailId);

}
