package com.vinsys.securitylabs.forcebrowsing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDAO1 extends JpaRepository<Role1, Long> {

}
