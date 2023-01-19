package com.vinsys.securitylabs.tokenExpiration.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vinsys.securitylabs.tokenExpiration.Userdata;

public interface SignInRepository extends JpaRepository<Userdata, Long> {
	
	Optional<Userdata> findByUsername(String username);
	
	@Query(value="select username from tbl_user_activity where is_active='Y'",nativeQuery = true)
	 String existsByUsername(String username);

	
	Userdata findByUsernameAndIsActiveAndTokenId(String username,String active,String tokenId);
	@Query(value = "select * from tbl_user_activity where username=?1 and is_active=?2",nativeQuery = true)
	List<Userdata> findAllUserByIdAndIsActive(String username,String active);
	

}
