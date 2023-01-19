package com.vinsys.securitylabs.forcebrowsing;

import java.util.List;

public class GetAllRoleResponseVO extends BaseResponseVO  {
	List<Roles>roleList;

	public List<Roles> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Roles> roleList) {
		this.roleList = roleList;
	}

	public GetAllRoleResponseVO(EActionStatus status, String message,List<Roles> roleList) {
		super(status, message);
		this.roleList = roleList;
	}
	public GetAllRoleResponseVO(EActionStatus status, String message) {
		super(status, message);
		
	}
	
	
}
