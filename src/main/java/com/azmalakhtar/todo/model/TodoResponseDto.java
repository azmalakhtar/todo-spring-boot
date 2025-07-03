package com.azmalakhtar.todo.model;

import java.time.LocalDate;

public record TodoResponseDto (
	Long id,
	String title,
	String description,
	LocalDate dueDate,
	Boolean isDone,
	Boolean isUrgent,
	Boolean isImportant
) {}
