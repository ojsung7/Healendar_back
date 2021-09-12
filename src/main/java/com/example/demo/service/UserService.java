package com.example.demo.service;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserEntity findById(String id) {
		return userRepository.findById(id);
	}
	
	public UserEntity saveUser(UserEntity userEntity) {
		RoleEntity userRole = roleRepository.findByName("USER");
		userEntity.setRoleEntity(userRole);
		userEntity.setPw(passwordEncoder.encode(userEntity.getPw()));		
		return userRepository.save(userEntity);
	}
	
	
	public UserEntity findByLoginAndPassword(String id, String pw) {
		UserEntity userEntity = findById(id);
		if(userEntity != null ) {
			if(passwordEncoder.matches(pw, userEntity.getPw()))
			{
				return userEntity;
			}
		}
		return null;
	}
}