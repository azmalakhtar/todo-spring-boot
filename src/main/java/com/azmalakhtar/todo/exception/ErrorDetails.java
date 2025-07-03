package com.azmalakhtar.todo.exception;

import java.time.LocalDateTime;

public record ErrorDetails (
	LocalDateTime time,
	String message,
	String description
){}
