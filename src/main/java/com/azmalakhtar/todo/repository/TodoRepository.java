package com.azmalakhtar.todo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azmalakhtar.todo.model.Todo;
import com.azmalakhtar.todo.model.TodoUser;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	List<Todo> findAll();
	List<Todo> findByTodoUser(TodoUser todoUser);
	List<Todo> findByTodoUserAndDueDate(TodoUser todoUser, LocalDate dueDate);
	void deleteByTodoUser(TodoUser todoUser);
}
