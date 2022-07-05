package com.hype.bookmarkmanager.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hype.bookmarkmanager.model.Account;
import com.hype.bookmarkmanager.service.impl.AccountService;


@Configuration
public class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {
	
	@Autowired
	private AccountService apiAccountService;
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				Account account = apiAccountService.findByUsername(username);
				if (account != null) {
					return new User(account.getUsername(), account.getPassword(), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList(account.getRole()));
				} else {
					throw new UsernameNotFoundException("Account: '" + username + "' not configured into BookmarkManager");
				}
			}
		};
	}
	
	
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 //return new BCryptPasswordEncoder();
		 return new PasswordDefaultEncoder();
	}
}
