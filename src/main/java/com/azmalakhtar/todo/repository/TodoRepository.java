package com.azmalakhtar.todo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.azmalakhtar.todo.model.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {
	List<Todo> findAll();
}
