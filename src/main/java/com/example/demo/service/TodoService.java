package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TodoEntity;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoService {
	@Autowired
	private TodoRepository todoRepository;
	
	public List<TodoEntity> findAll() {
		return todoRepository.findAll();
	}
	
	public TodoEntity findTodo(String id, Date date) {
		return todoRepository.findTodo(id, date);
	}

}
