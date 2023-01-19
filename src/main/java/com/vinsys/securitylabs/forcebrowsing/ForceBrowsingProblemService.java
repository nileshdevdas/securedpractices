package com.vinsys.securitylabs.forcebrowsing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class ForceBrowsingProblemService implements IForceBrowsingProblem {
	
	
	@Autowired
	IRoleDAO roleRepository;

	@Override
	public GetAllRoleResponseVO viewRoleList(Long id) {
		try {

			if (id == null || id < 0 || id == 0) {
				throw new IllegalArgumentException();
			}
			Optional<Roles> user = roleRepository.findById(id);
			if (user.isPresent()) {
				List<Roles> savedUser = new ArrayList<>();
				savedUser.add(user.get());
				return new GetAllRoleResponseVO(EActionStatus.SUCCESS, Constants.INVALID_CREDIENTIAL,
						savedUser);
			} else {
				throw new IllegalArgumentException("User not present");
			}
		
		} catch (Exception e) {
			return new GetAllRoleResponseVO(EActionStatus.EXCEPTION,
					Constants.USER_NOT_FOUND);
		}
	}

}
