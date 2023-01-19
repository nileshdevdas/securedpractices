package com.vinsys.securitylabs.bruteforce.solution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



import com.vinsys.securitylabs.bruteforce.BruteUsers;

public interface IBruteForceSolutionRepository extends JpaRepository<BruteUsers, Long> {

	BruteUsers findByEmail(String email);

	BruteUsers findByEmailAndPassword(String email, String pasword);

	@Query("UPDATE BruteUsers u SET u.failedAttempt = ?1 WHERE u.email = ?2")
	@Modifying
	public void updateFailedAttempts(int failAttempts, String email);
}
