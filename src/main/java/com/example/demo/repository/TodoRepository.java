package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
	
	@Query(value="select * from todo where id=:id and date=:date", nativeQuery = true)
	TodoEntity findTodo(String id,Date date);
}
