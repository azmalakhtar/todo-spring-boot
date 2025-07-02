package com.azmalakhtar.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azmalakhtar.todo.model.TodoUser;

public interface TodoUserRepository extends JpaRepository<TodoUser, Long> {
	Optional<TodoUser> findByEmail(String email);
}
