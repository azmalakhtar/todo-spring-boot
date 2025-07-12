package com.azmalakhtar.todo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String title;
	private String description;
	private LocalDate dueDate;
	@Column(nullable = false)
	private Boolean isDone = false;
	@Column(nullable = false)
	private Boolean isUrgent = false;
	@Column(nullable = false)
	private Boolean isImportant = false;
	@ManyToOne
	@JoinColumn(name = "todo_user_id")
	private TodoUser todoUser;
}
