package com.azmalakhtar.todo.exception;

public class TodoNotFoundException extends RuntimeException {
	public TodoNotFoundException(Long id) {
		super("Todo with ID " + id + " not found.");
	}

	public TodoNotFoundException(String msg) {
		super(msg);
	}
}
