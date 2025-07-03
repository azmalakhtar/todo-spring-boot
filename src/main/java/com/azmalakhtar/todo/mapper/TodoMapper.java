package com.azmalakhtar.todo.mapper;

import org.springframework.stereotype.Component;

import com.azmalakhtar.todo.model.Todo;
import com.azmalakhtar.todo.model.TodoRequestDto;
import com.azmalakhtar.todo.model.TodoResponseDto;

@Component
public class TodoMapper {
	public Todo toTodo(TodoRequestDto requestDto) {
		return new Todo(
			null,
			requestDto.title(),
			requestDto.description(),
			requestDto.dueDate(),
			requestDto.isDone(),
			requestDto.isUrgent(),
			requestDto.isImportant(),
			null
		);
	}

	public TodoResponseDto toResponseDto(Todo todo) {
		return new TodoResponseDto(
			todo.getId(),
			todo.getTitle(),
			todo.getDescription(),
			todo.getDueDate(),
			todo.getIsDone(),
			todo.getIsUrgent(),
			todo.getIsImportant()
		);
	}
}
