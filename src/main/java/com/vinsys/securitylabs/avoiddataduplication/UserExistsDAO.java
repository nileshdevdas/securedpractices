package com.vinsys.securitylabs.avoiddataduplication;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserExistsDAO extends JpaRepository<userEntity, Long> {
	
	public userEntity findByemailId(String emailId);
	
}
