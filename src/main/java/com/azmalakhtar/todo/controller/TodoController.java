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
import com.azmalakhtar.todo.model.TodoRequestDto;
import com.azmalakhtar.todo.model.TodoResponseDto;
import com.azmalakhtar.todo.repository.TodoRepository;
import com.azmalakhtar.todo.service.TodoService;

@RestController
public class TodoController {
	private TodoService service;

	public TodoController(TodoService service) {
		this.service = service;
	}

	@PostMapping("/todos")
	public ResponseEntity<TodoResponseDto> createTodo(@RequestBody @Validated TodoRequestDto requestDto, Authentication authentication) {
		TodoResponseDto responseDto = service.create(authentication.getName(), requestDto);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(responseDto);
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long id, Authentication authentication) {
		TodoResponseDto responseDto = service.getById(authentication.getName(), id);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(responseDto);
	}

	@GetMapping("/todos")
	public ResponseEntity<List<TodoResponseDto>> getAllTodos(Authentication authentication) {
		List<TodoResponseDto> responseDtos = service.getAll(authentication.getName());
		return ResponseEntity.status(HttpStatus.OK)
			.body(responseDtos);
	}

	@DeleteMapping("/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable Long id, Authentication authentication) {
		service.deleteById(authentication.getName(), id);
		return ResponseEntity.noContent().build(); 
	}


	@PatchMapping("/todos/{id}")
	public ResponseEntity<TodoResponseDto> updateTodo(
		@RequestBody TodoRequestDto requestDto, @PathVariable Long id, Authentication authentication
	) {
		TodoResponseDto responseDto = service.update(authentication.getName(), id, requestDto);
		return ResponseEntity.status(HttpStatus.OK)
			.body(responseDto);
	}

	@DeleteMapping("/todos")
	public void deleteAll(Authentication authentication) {
		service.deleteAll(authentication.getName());
	}
}
