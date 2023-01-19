package com.vinsys.securitylabs.forcebrowsing;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleDAO extends JpaRepository<Roles, Long> {

	

}
