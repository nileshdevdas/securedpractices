package com.vinsys.securitylabs.leastpriviledge.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinsys.securitylabs.leastpriviledge.entity.ERole;
import com.vinsys.securitylabs.leastpriviledge.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
	  Optional<Role> findByName(ERole name);

}
