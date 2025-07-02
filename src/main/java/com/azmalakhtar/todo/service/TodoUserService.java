package com.azmalakhtar.todo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

import com.azmalakhtar.todo.model.Todo;
import com.azmalakhtar.todo.model.TodoUser;
import com.azmalakhtar.todo.model.TodoUserCredentials;
import com.azmalakhtar.todo.repository.TodoRepository;
import com.azmalakhtar.todo.repository.TodoUserRepository;

@Service
public class TodoUserService {
	private TodoUserRepository repository;
	private AuthenticationManager authManager;
	private JwtService jwtService;
	private BCryptPasswordEncoder passwordEncoder;

	public TodoUserService(BCryptPasswordEncoder passwordEncoder, TodoUserRepository repository,
			AuthenticationManager authManager, JwtService jwtService) {
		this.passwordEncoder = passwordEncoder;
		this.repository = repository;
		this.authManager = authManager;
		this.jwtService = jwtService;
	}

	public boolean register(TodoUserCredentials userCredentials) {
		Optional<TodoUser> td = repository.findByEmail(userCredentials.email());
		if (td.isPresent()) return false;

		TodoUser user = new TodoUser(null, userCredentials.email(), userCredentials.password(), null);
		user.setPassword(passwordEncoder.encode(userCredentials.password()));
		try {
			repository.save(user);
			return true;
		} catch(Exception exception) {
			return false;
		}
	}

	public Optional<String> login(TodoUserCredentials userCredentials) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(userCredentials.email(), userCredentials.password());
		authentication = authManager.authenticate(authentication);

		if (authentication.isAuthenticated()) {
			String token = jwtService.generateToken(authentication);
			return Optional.of(token);
		}

		return Optional.empty();
	}

	public void deleteAll() {
		repository.deleteAll();
	}
}
