package com.azmalakhtar.todo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.azmalakhtar.todo.exception.TodoNotFoundException;
import com.azmalakhtar.todo.mapper.TodoMapper;
import com.azmalakhtar.todo.model.Todo;
import com.azmalakhtar.todo.model.TodoRequestDto;
import com.azmalakhtar.todo.model.TodoResponseDto;
import com.azmalakhtar.todo.model.TodoUser;
import com.azmalakhtar.todo.repository.TodoRepository;
import com.azmalakhtar.todo.repository.TodoUserRepository;

import jakarta.transaction.Transactional;

@Service
public class TodoService {
	private TodoRepository todoRepository;
	private TodoUserRepository userRepository;
	private TodoMapper mapper;


	public TodoService(TodoMapper mapper, TodoRepository todoRepository, TodoUserRepository userRepository) {
		this.mapper = mapper;
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;
	}

	public TodoResponseDto create(String email, TodoRequestDto requestDto) {
		TodoUser user = userRepository.findByEmail(email).get();
		Todo todo = mapper.toTodo(requestDto);
		todo.setTodoUser(user);
		todo = todoRepository.save(todo);
		return mapper.toResponseDto(todo);
	}

	public TodoResponseDto getById(String email, Long id) {
		Todo todo = todoRepository.findById(id)
			.orElseThrow(() -> new TodoNotFoundException(id));
		TodoUser user = userRepository.findByEmail(email).get();
		if (todo.getTodoUser().getId() != user.getId()) throw new TodoNotFoundException(id);

		return mapper.toResponseDto(todo);
	}


	public List<TodoResponseDto> getAll(String email) {
		TodoUser user = userRepository.findByEmail(email).get();
		List<Todo> todos = todoRepository.findByTodoUser(user);
		List<TodoResponseDto> responseDtos = todos.stream()
			.map(todo -> mapper.toResponseDto(todo))
			.toList();
		return responseDtos;
	}


	public void deleteById(String email, Long id) {
		Todo todo = todoRepository.findById(id)
			.orElseThrow(() -> new TodoNotFoundException(id));
		TodoUser user = userRepository.findByEmail(email).get();
		if (!todo.getTodoUser().getId().equals(user.getId())) throw new TodoNotFoundException(id);
		todoRepository.deleteById(id);
	}

	public TodoResponseDto update(String email, Long id, TodoRequestDto requestDto) {
		Todo todo = todoRepository.findById(id)
			.orElseThrow(() -> new TodoNotFoundException(id));
		TodoUser user = userRepository.findByEmail(email).get();
		if (!todo.getTodoUser().getId().equals(user.getId())) throw new TodoNotFoundException(id);

		if (requestDto.title() != null) todo.setTitle(requestDto.title());
		if (requestDto.description() != null) todo.setDescription(requestDto.description());
		if (requestDto.dueDate() != null) todo.setDueDate(requestDto.dueDate());
		if (requestDto.isDone() != null) todo.setIsDone(requestDto.isDone());
		if (requestDto.isUrgent() != null) todo.setIsUrgent(requestDto.isUrgent());
		if (requestDto.isImportant() != null) todo.setIsImportant(requestDto.isImportant());

		todo = todoRepository.save(todo);
		return mapper.toResponseDto(todo);
	}

	public List<TodoResponseDto> getAllOfDueDate(LocalDate dueDate, String email) {
		TodoUser user = userRepository.findByEmail(email).get();
		List<Todo> todos = todoRepository.findByTodoUserAndDueDate(user, dueDate);
		List<TodoResponseDto> responseDtos = todos.stream()
			.map(todo -> mapper.toResponseDto(todo))
			.toList();
		return responseDtos;
	}

	@Transactional
	public void deleteAll(String email) {
		TodoUser user = userRepository.findByEmail(email).get();
		todoRepository.deleteByTodoUser(user);
	}


}
