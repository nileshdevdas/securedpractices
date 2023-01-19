package com.vinsys.securitylabs.commentinformation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewUserDAO extends JpaRepository<UserInfo, Long> {
	
	Optional<UserInfo> findById(Long id);
	 List<UserInfo> findAll();

}
