package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TodoEntity;
import com.example.demo.service.TodoService;

@CrossOrigin
@RestController
@RequestMapping("/todo")
public class TodoController {
	@Autowired
	private TodoService todoService;
	
	@PostMapping("/get")
	public List<TodoEntity> todo() {
		return todoService.findAll();
	}
	
	@PostMapping("/findTodo")
	public TodoEntity findTodo(@RequestBody TodoEntity request) {
		Date tmpDate = request.getDate();
		return todoService.findTodo(request.getId(), tmpDate);
	}
}