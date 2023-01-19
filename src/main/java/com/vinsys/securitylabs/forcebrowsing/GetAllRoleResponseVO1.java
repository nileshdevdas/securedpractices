package com.vinsys.securitylabs.forcebrowsing;

import java.util.List;

public class GetAllRoleResponseVO1  extends BaseResponseVO{

	List<Role1>roleList;

	public List<Role1> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role1> roleList) {
		this.roleList = roleList;
	}

	public GetAllRoleResponseVO1(EActionStatus status, String message,List<Role1> roleList) {
		super(status, message);
		this.roleList = roleList;
	}
	public GetAllRoleResponseVO1(EActionStatus status, String message) {
		super(status, message);
		
	}
	
	

}
