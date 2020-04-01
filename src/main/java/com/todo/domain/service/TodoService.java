package com.todo.domain.service;

import com.todo.domain.model.Todo;

import java.util.List;

public interface TodoService {
  List<Todo> retrieveAll();
}
