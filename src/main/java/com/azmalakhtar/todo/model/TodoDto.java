package com.azmalakhtar.todo.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;

public record TodoDto(
	@NotEmpty
	String title,
	@NotEmpty
	String description,
	LocalDate dueDate,
	Boolean isDone,
	Boolean isUrgent,
	Boolean isImportant
) {}
