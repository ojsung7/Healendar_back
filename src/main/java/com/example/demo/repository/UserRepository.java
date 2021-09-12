package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.TodoEntity;
import com.example.demo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	UserEntity findById(String id);
}