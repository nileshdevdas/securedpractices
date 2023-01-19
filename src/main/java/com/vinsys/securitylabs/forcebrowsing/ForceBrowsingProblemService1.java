package com.vinsys.securitylabs.forcebrowsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForceBrowsingProblemService1 implements IForceBrowsingProblem1 {

	@Autowired
	IRoleDAO1 roleRepository;
	
	@Override
	public GetAllRoleResponseVO1 viewRoleList1(Long id) {

		try {

			if (id == null || id < 0 || id == 0) {
				throw new IllegalArgumentException();
			}
			Optional<Role1> user = roleRepository.findById(id);
			if (user.isPresent()) {
				List<Role1> savedUser = new ArrayList<>();
				savedUser.add(user.get());
				return new GetAllRoleResponseVO1(EActionStatus.SUCCESS, Constants.INVALID_CREDIENTIAL,
						savedUser);
			} else {
				throw new IllegalArgumentException("User not present");
			}
		
		} catch (Exception e) {
			return new GetAllRoleResponseVO1(EActionStatus.EXCEPTION,
					Constants.USER_NOT_FOUND);
		}	
		
	}
	
	
	

}
