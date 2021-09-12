package com.example.demo;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public CustomUserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		UserEntity userEntity = userService.findById(id);
		return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
	}
}