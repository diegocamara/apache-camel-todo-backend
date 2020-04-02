package com.todo.domain.repository;

import com.todo.domain.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
  Todo store(Todo todo);

  List<Todo> findAll();

  void deleteAll();

  Optional<Todo> find(Long id);

  void delete(Long id);
}
