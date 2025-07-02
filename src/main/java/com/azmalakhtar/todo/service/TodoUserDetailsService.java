package com.azmalakhtar.todo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.azmalakhtar.todo.model.TodoUser;
import com.azmalakhtar.todo.model.TodoUserDetails;
import com.azmalakhtar.todo.repository.TodoUserRepository;

@Service
public class TodoUserDetailsService implements UserDetailsService {
	private TodoUserRepository repository;

	public TodoUserDetailsService(TodoUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<TodoUser> user = repository.findByEmail(username);
		if (user.isEmpty()) throw new UsernameNotFoundException(username + " not found.");
		return new TodoUserDetails(user.get());
	}
}
