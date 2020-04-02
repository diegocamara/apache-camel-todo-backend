package com.todo.infrastructure.persistence.springdata;

import com.todo.domain.model.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepositorySpringDataJPA extends JpaRepository<Todo, Long> {}
