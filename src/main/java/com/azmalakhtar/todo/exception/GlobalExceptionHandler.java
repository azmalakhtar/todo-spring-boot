package com.azmalakhtar.todo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(TodoNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleTodoNotFound(TodoNotFoundException ex, WebRequest request) {
		var errorDetails = new ErrorDetails(
			LocalDateTime.now(),
			ex.getMessage(),
			request.getDescription(false)
		);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}
}
