package com.vinsys.securitylabs.leastpriviledge.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vinsys.securitylabs.leastpriviledge.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query(value = "select username from tbl_mst_users where is_active='Y'", nativeQuery = true)
	String existsByUser(String username);

	@Query("select login from User login where username=?1")
	public User findByCustomQuery(String username);
}
