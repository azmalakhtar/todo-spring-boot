package com.azmalakhtar.todo.model;

import jakarta.validation.constraints.NotEmpty;

public record TodoUserCredentials(
	@NotEmpty(message = "email can't be empty")
	String email, 
	@NotEmpty(message = "password can't be empty")
	String password
) {}
