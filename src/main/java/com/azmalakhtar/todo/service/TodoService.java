package com.azmalakhtar.todo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.azmalakhtar.todo.model.Todo;
import com.azmalakhtar.todo.model.TodoDto;
import com.azmalakhtar.todo.model.TodoUser;
import com.azmalakhtar.todo.repository.TodoRepository;
import com.azmalakhtar.todo.repository.TodoUserRepository;

@Service
public class TodoService {
	private TodoRepository todoRepository;
	private TodoUserRepository userRepository;


	public TodoService(TodoRepository todoRepository, TodoUserRepository userRepository) {
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;
	}


	public TodoDto create(String email, TodoDto todoDto) {
		TodoUser user = userRepository.findByEmail(email).get();
		Todo todo = new Todo(
			null, 
			todoDto.title(), 
			todoDto.description(), 
			todoDto.dueDate(),
			todoDto.isDone(),
			todoDto.isImportant(),
			todoDto.isUrgent(),
			user
		);
		todo = todoRepository.save(todo);
		return new TodoDto(todo.getTitle(), todo.getDescription(), todo.getDueDate(), todo.getIsDone(), todo.getIsImportant(), todo.getIsUrgent());
	}
}
