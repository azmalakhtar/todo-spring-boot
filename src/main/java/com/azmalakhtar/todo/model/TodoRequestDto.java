package com.azmalakhtar.todo.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;

public record TodoRequestDto(
	@NotEmpty
	String title,
	String description,
	LocalDate dueDate,
	Boolean isDone,
	Boolean isUrgent,
	Boolean isImportant
) {}
