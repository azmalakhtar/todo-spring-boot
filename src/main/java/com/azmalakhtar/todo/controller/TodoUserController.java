package com.azmalakhtar.todo.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azmalakhtar.todo.model.TodoUserCredentials;
import com.azmalakhtar.todo.service.TodoUserService;

@RestController
public class TodoUserController {
	private TodoUserService service;

	public TodoUserController(TodoUserService service) {
		this.service = service;
	}

	@GetMapping("/test")
	public String test(Authentication authentication) {
		return "TEST: SUCCESFUL\n" + authentication.getName();
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @Validated TodoUserCredentials userCredentials) {
		boolean save = service.register(userCredentials);
		if (!save) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while registering\n");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body("User " + userCredentials.email() + " has been registered\n");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Validated TodoUserCredentials userCredentials) {
		Optional<String> token = service.login(userCredentials);
		if (token.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body("Invalid username or password\n");
		}
		return ResponseEntity.status(HttpStatus.OK)
			.body(token.get());
	}

	@DeleteMapping("/all")
	public void deleteAll() {
		service.deleteAll();
	}
}
