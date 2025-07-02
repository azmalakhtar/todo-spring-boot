package com.azmalakhtar.todo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azmalakhtar.todo.model.Todo;
import com.azmalakhtar.todo.model.TodoDto;
import com.azmalakhtar.todo.repository.TodoRepository;
import com.azmalakhtar.todo.service.TodoService;

@RestController
public class TodoController {
	private TodoService service;

	public TodoController(TodoService service) {
		this.service = service;
	}

	@PostMapping("/todos")
	public ResponseEntity<TodoDto> createTodo(@RequestBody @Validated TodoDto todoDto, Authentication authentication) {
		TodoDto todo = service.create(authentication.getName(), todoDto);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(todo);
	}

	/*
	@GetMapping("/todos")
	public List<Todo> getAllTodos() {
		return repository.findAll();
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
		Optional<Todo> todo = repository.findById(id);
		if (todo.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(null);
		}
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(todo.get());
	}


	@PatchMapping("/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable Long id) {
		Optional<Todo> oldTodoOptional = repository.findById(id);
		if (oldTodoOptional.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(null);
		}
		Todo oldTodo = oldTodoOptional.get();
		if (todo.getTitle() != null)
			oldTodo.setTitle(todo.getTitle());
		if (todo.getDescription() != null)
			oldTodo.setDescription(todo.getDescription());
		if (todo.getDueDate() != null)
			oldTodo.setDueDate(todo.getDueDate());
		if (todo.getIsDone() != null)
			oldTodo.setIsDone(todo.getIsDone());
		if (todo.getIsUrgent() != null)
			oldTodo.setIsUrgent(todo.getIsUrgent());
		if (todo.getIsImportant() != null)
			oldTodo.setIsImportant(todo.getIsImportant());

		repository.save(oldTodo);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(oldTodo);
	}

	@DeleteMapping("/todos/{id}")
	public void deleteTodo(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@DeleteMapping("/todos/all")
	public void deleteAll() {
		repository.deleteAll();
	}
	*/
}
