package com.azmalakhtar.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.azmalakhtar.todo.model.Todo;
import com.azmalakhtar.todo.model.TodoUser;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	List<Todo> findAll();
	List<Todo> findByTodoUser(TodoUser todoUser);
	void deleteByTodoUser(TodoUser todoUser);
}
