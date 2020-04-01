package com.todo.domain.service.impl;

import com.todo.domain.model.Todo;
import com.todo.domain.repository.TodoRepository;
import com.todo.domain.service.TodoService;

import java.util.LinkedList;
import java.util.List;

public class TodoServiceImpl implements TodoService {

  private TodoRepository todoRepository;

  public TodoServiceImpl(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Override
  public List<Todo> retrieveAll() {
    return new LinkedList<>();
  }
}
