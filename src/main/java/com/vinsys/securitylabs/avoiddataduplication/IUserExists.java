package com.vinsys.securitylabs.avoiddataduplication;

import com.vinsys.securitylabs.avoiddataduplication.VO.UserExistsResponseVO;
import com.vinsys.securitylabs.avoiddataduplication.VO.UserExistsSolutionResponseVO;
import com.vinsys.securitylabs.avoiddataduplication.VO.addUserRequestVO;

public interface IUserExists {
	
	public UserExistsResponseVO addUser(addUserRequestVO user);
	public UserExistsSolutionResponseVO addUserSolution(addUserRequestVO user);

}
