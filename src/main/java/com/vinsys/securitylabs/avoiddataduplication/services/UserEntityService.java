package com.vinsys.securitylabs.avoiddataduplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinsys.securitylabs.avoiddataduplication.IUserExists;
import com.vinsys.securitylabs.avoiddataduplication.UserExistsDAO;
import com.vinsys.securitylabs.avoiddataduplication.userEntity;
import com.vinsys.securitylabs.avoiddataduplication.VO.UserExistsResponseVO;
import com.vinsys.securitylabs.avoiddataduplication.VO.UserExistsSolutionResponseVO;
import com.vinsys.securitylabs.avoiddataduplication.VO.addUserRequestVO;
import com.vinsys.securitylabs.forcebrowsing.Constants;
import com.vinsys.securitylabs.forcebrowsing.EActionStatus;

@Service
public class UserEntityService implements IUserExists {
	
	@Autowired
	UserExistsDAO userexistsdao;

	@Override
	public UserExistsResponseVO addUser(addUserRequestVO user) {
		try {
			userEntity entity = new userEntity();
			entity.setName(user.getName());
			entity.setEmail_id(user.getEmail_id());
			entity.setContact_no(user.getContact_no());
			userexistsdao.save(entity);
		}catch(IllegalArgumentException ex) {
			return new UserExistsResponseVO(EActionStatus.FAILURE,ex.getMessage());
		}
		// TODO Auto-generated method stub
		return new UserExistsResponseVO(EActionStatus.SUCCESS,Constants.USER_CREATE_SUCCES);
	}

	@Override
	public UserExistsSolutionResponseVO addUserSolution(addUserRequestVO user) {
		try {
			
			userEntity email_id = null;
			email_id = userexistsdao.findByemailId(user.getEmail_id());
			if(email_id==null) {
				userEntity add = new userEntity();
				add.setName(user.getName());
				add.setEmail_id(user.getEmail_id());
				add.setContact_no(user.getContact_no());
				userexistsdao.save(add);
			} else {
				if(email_id!=null){
					throw new IllegalArgumentException(Constants.USER_ALREADY_EXISTS);
				} 
			}
			
		} catch(IllegalArgumentException ex) {
			return new UserExistsSolutionResponseVO(EActionStatus.FAILURE, ex.getMessage());
		}
		// TODO Auto-generated method stub
		return new UserExistsSolutionResponseVO(EActionStatus.SUCCESS, Constants.USER_CREATE_SUCCES);
	}
	
	

}
