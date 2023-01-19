package com.vinsys.securitylabs.forcebrowsing.solution;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.forcebrowsing.GetAllRoleResponseVO;
import com.vinsys.securitylabs.forcebrowsing.GetAllRoleResponseVO1;
import com.vinsys.securitylabs.forcebrowsing.IForceBrowsingProblem;
import com.vinsys.securitylabs.forcebrowsing.IForceBrowsingProblem1;
import com.vinsys.securitylabs.leastpriviledge.entity.ERole;
import com.vinsys.securitylabs.leastpriviledge.entity.Role;
import com.vinsys.securitylabs.leastpriviledge.repo.RoleRepository;
import com.vinsys.securitylabs.leastpriviledge.request.BaseResponse;
import com.vinsys.securitylabs.leastpriviledge.util.AuthorityHelper;

@RestController(value = "forcebrowsingsolution1")
@RequestMapping(path = "/forcebrowser/solution1")
public class ForceBrowsingSolution1Controller {


	
	@Autowired
	IForceBrowsingProblem1 forceservice;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	AuthorityHelper authHelper;
//	
//	@GetMapping("/getAllRole/{id}")
//	public GetAllRoleResponseVO1 getOrgByUser(@PathVariable("id") Long Id){ 
//   return forceservice.viewRoleList1(Id);
//		
//}
	@GetMapping("/getAllRole/{id}")
	public BaseResponse getRole(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@PathVariable("id") Long Id) {
		BaseResponse response=new BaseResponse();
		try {
			
			boolean isRolePresent=authHelper.getRolePresent(httpServletRequest,ERole.ROLE_ADMIN.name());
			
			if(isRolePresent) {
				 response.setResponseBody(forceservice.viewRoleList1(Id));
				 
			}else {
				throw new IllegalArgumentException("Unauthrorized User For This API.");
			}
			
		
		} catch(IllegalArgumentException e) {
			response.setResponseMessage(e.getMessage());	
		}
		catch (Exception e) {
			response.setResponseMessage("Exception");
		}
		return response;
	}



}
