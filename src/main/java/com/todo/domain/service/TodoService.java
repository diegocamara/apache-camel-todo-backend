package com.todo.domain.service;

import com.todo.domain.model.NewTodo;
import com.todo.domain.model.Todo;
import com.todo.domain.model.UpdateTodo;

import java.util.List;

public interface TodoService {
  List<Todo> findAll();

  Todo find(Long id);

  Todo create(NewTodo newTodo);

  Todo update(UpdateTodo updateTodo);

  void deleteAll();

  void delete(Long id);
}
