package com.vinsys.securitylabs.leastpriviledge.solution;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.leastpriviledge.entity.ERole;
import com.vinsys.securitylabs.leastpriviledge.entity.Role;
import com.vinsys.securitylabs.leastpriviledge.repo.RoleRepository;
import com.vinsys.securitylabs.leastpriviledge.request.BaseResponse;
import com.vinsys.securitylabs.leastpriviledge.util.AuthorityHelper;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/solution")
public class UserControllerSolution {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerSolution.class);
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	AuthorityHelper authHelper;
	 
	@GetMapping("/getroles")
	public BaseResponse getRole(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		BaseResponse response=new BaseResponse();
		try {
			
			boolean isRolePresent=authHelper.getRolePresent(httpServletRequest,ERole.ROLE_ADMIN.name());
			
			if(isRolePresent) {
				
				List<Role> roleList=roleRepository.findAll();
				
				response.setResponseBody(roleList);
				response.setResponseCode(101);
				response.setResponseMessage("Get Roles SuccessFully..!!");
				response.setTotalRecords(roleList.size());
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

