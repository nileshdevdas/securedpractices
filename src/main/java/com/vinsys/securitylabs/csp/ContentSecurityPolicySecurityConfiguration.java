package com.vinsys.securitylabs.csp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vinsys.securitylabs.leastpriviledge.jwt.AuthEntryPointJwt;
import com.vinsys.securitylabs.leastpriviledge.jwt.AuthTokenFilter;
import com.vinsys.securitylabs.leastpriviledge.security.UserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class ContentSecurityPolicySecurityConfiguration {
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
		
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.headers().xssProtection().and().contentSecurityPolicy(
				 "default-src 'self'; script-src 'self' style-src 'self'; img-src ; font-src ; connect-src 'self'; media-src *; object-src 'none'; frame-src 'self'; worker-src 'self'; frame-ancestors 'self'; form-action 'self'; upgrade-insecure-requests; block-all-mixed-content; disown-opener ");

		http.sessionManagement().sessionFixation().migrateSession();
	

		http.sessionManagement().sessionFixation().migrateSession();		

		http.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests().antMatchers("/js/jquery-min.js", "/api/auth/signin/**","/api/auth/**", "/forcebrowser/problem/**",
				"/forcebrowser/problem1/**", "/login/**", "/xssreflectedform/problem/**","/reflectedproblem/problem/**","/inputvalidationsolution/solution/**","/inputvalidationproblem/problem/**",
				"/getReflectedCandidate","/problem/candidate-form","/api/auth/getCaptcha","/api/auth/getOtp/")
		.permitAll().anyRequest().authenticated();

	    
	    http.authenticationProvider(authenticationProvider());

	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	}
	
	@Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	  
	  @Bean
	  public SecurityFilterChain Logoutfilterchain(HttpSecurity security) throws Exception {
		  security
			.headers(headers -> headers
				.cacheControl(cache -> cache.disable())
			);
		  return security.build();
	  }
	  
}
