package com.todo.domain.service.impl;

import com.todo.domain.exception.TodoNotFoundException;
import com.todo.domain.model.NewTodo;
import com.todo.domain.model.Todo;
import com.todo.domain.model.UpdateTodo;
import com.todo.domain.repository.TodoRepository;
import com.todo.domain.service.TodoService;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import javax.transaction.Transactional;

public class TodoServiceImpl implements TodoService {

  private TodoRepository todoRepository;

  public TodoServiceImpl(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Override
  @Transactional
  public List<Todo> findAll() {
    return todoRepository.findAll();
  }

  @Override
  public Todo find(Long id) {
    return todoRepository.find(id).orElseThrow(() -> new TodoNotFoundException(id));
  }

  @Override
  @Transactional
  public Todo create(NewTodo newTodo) {
    return todoRepository.store(fromNewTodo(newTodo));
  }

  @Override
  @Transactional
  public Todo update(UpdateTodo updateTodo) {

    Todo todo =
        todoRepository
            .find(updateTodo.getId())
            .orElseThrow(() -> new TodoNotFoundException(updateTodo.getId()));

    configFieldIfPresent(updateTodo.getTitle(), todo::setTitle);
    configFieldIfPresent(updateTodo.getCompleted(), todo::setCompleted);
    configFieldIfPresent(updateTodo.getOrder(), todo::setOrder);

    return todo;
  }

  @Override
  @Transactional
  public void deleteAll() {
    todoRepository.deleteAll();
  }

  @Override
  @Transactional
  public void delete(Long id) {
    todoRepository.delete(id);
  }

  private Todo fromNewTodo(NewTodo newTodo) {
    Todo todo = new Todo();
    todo.setTitle(newTodo.getTitle());
    todo.setOrder(newTodo.getOrder());
    return todo;
  }

  private <T> void configFieldIfPresent(T value, Consumer<T> consumer) {
    if (Objects.nonNull(value)) {
      consumer.accept(value);
    }
  }
}
