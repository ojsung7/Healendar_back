package com.example.demo.controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import com.example.demo.config.jwt.JwtProvider;
import com.example.demo.entity.TodoEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.TodoService;
import com.example.demo.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/main")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private TodoService todoService;
	@Autowired
	private JwtProvider jwtProvider;

	@PostMapping("/register")
	public String registerUser(@RequestBody @Valid UserEntity request) {
		try {
			UserEntity u = new UserEntity();
			u.setId(request.getId());
			u.setPw(request.getPw());
			userService.saveUser(u);
			return "OK";
		} catch (Exception e) {
			System.out.println(e);
			return "다시 확인해주세요";
		}

	}

	@PostMapping("/auth")
	public String auth(@RequestBody UserEntity request) {
		try {
			UserEntity userEntity = userService.findByLoginAndPassword(request.getId(), request.getPw());
			String role = userEntity.getRoleEntity().getName();
			String token = jwtProvider.generateToken(userEntity.getId(), role);
			return token;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}