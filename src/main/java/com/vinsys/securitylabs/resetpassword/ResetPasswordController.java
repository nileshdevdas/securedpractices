package com.vinsys.securitylabs.resetpassword;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.securitylabs.leastpriviledge.entity.User;
import com.vinsys.securitylabs.leastpriviledge.repo.UserRepository;
import com.vinsys.securitylabs.leastpriviledge.util.AuthorityHelper;

import io.jsonwebtoken.Claims;

import com.vinsys.securitylabs.utils.ISecurityConstatnt;
import com.vinsys.securitylabs.utils.SecurityHelper;


@RestController
@RequestMapping(path = "/login")
public class ResetPasswordController {
	
	
	@Autowired
	UserRepository repo;

	@Autowired
	AuthorityHelper authorityHelper;

	@Autowired
	PasswordEncoder encoder;

	@RequestMapping(path = "/changePassword", method = {
			RequestMethod.PUT }, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String changePassword(@RequestBody ResetPassRequestVO request, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws IOException {
		try {
			if (!SecurityHelper.isNullOrEmpty(request.getUsername())
					&& !SecurityHelper.isNullOrEmpty(request.getPassword())
					&& !SecurityHelper.isNullOrEmpty(request.getNewPassword())) {

				// FIRST TIME LOGIN IS NOT IMPLEMENTED YET
				User entity = repo.findByCustomQuery(request.getUsername());


				if (!SecurityHelper.isNullOrEmpty(entity)) {
					String token = httpRequest.getHeader("Authorization");
					System.out.println(token);
					token = token.substring(7, token.length());
					Claims claims = authorityHelper.getLoggedInUserClaims(httpRequest);

					String logedInUser = claims.get("username").toString();


					if (!SecurityHelper.isNullOrEmpty(logedInUser) && logedInUser.equals(request.getUsername())) {

						if (entity != null && !entity.getIsActive().equalsIgnoreCase(ISecurityConstatnt.isNotActive)) {

							if (request != null && !SecurityHelper.isNullOrEmpty(request.getNewPassword())) {

								entity.setPassword(encoder.encode(request.getNewPassword()));
								entity.setIsFirstLogin("N");

								repo.save(entity);

								return SecurityHelper.sendSuccessResponse(
										ISecurityConstatnt.PasswordChangeSuccesfullyMessage,
										ISecurityConstatnt.successCode);
							}
						}
					} else {
						throw new com.vinsys.securitylabs.exception.SecurityException(ISecurityConstatnt.INVALID_USER,
								ISecurityConstatnt.INVALID_USER_LOGIN);
					}
				} else {
					throw new com.vinsys.securitylabs.exception.SecurityException(
							ISecurityConstatnt.AuthenticationFailedCode,
							ISecurityConstatnt.AuthenticationFailedMessage);
				}

			} else {
				throw new com.vinsys.securitylabs.exception.SecurityException(ISecurityConstatnt.InsufficientDataCode,
						ISecurityConstatnt.InsufficientDataMessage);
			}
		} catch (SecurityException hre) {
			hre.printStackTrace();
			return SecurityHelper.sendErrorResponse("ER", 22);
		} catch (Exception e) {
			e.printStackTrace();
			return SecurityHelper.sendErrorResponse(ISecurityConstatnt.UnknowErrorMessage,
					ISecurityConstatnt.UnknowErrorCode);
		}
		return null;
	}


}
