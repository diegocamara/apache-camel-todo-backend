package com.todo.infrastructure.persistence.repository;

import com.todo.domain.model.Todo;
import com.todo.domain.repository.TodoRepository;
import com.todo.infrastructure.persistence.springdata.TodoRepositorySpringDataJPA;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepositoryHibernate implements TodoRepository {

  private TodoRepositorySpringDataJPA todoRepositorySpringDataJPA;

  public TodoRepositoryHibernate(TodoRepositorySpringDataJPA todoRepositorySpringDataJPA) {
    this.todoRepositorySpringDataJPA = todoRepositorySpringDataJPA;
  }

  @Override
  public Todo store(Todo todo) {
    return todoRepositorySpringDataJPA.save(todo);
  }

  @Override
  public List<Todo> findAll() {
    return todoRepositorySpringDataJPA.findAll();
  }

  @Override
  public Optional<Todo> find(Long id) {
    return todoRepositorySpringDataJPA.findById(id);
  }

  @Override
  public void delete(Long id) {
    todoRepositorySpringDataJPA.deleteById(id);
  }

  @Override
  public void deleteAll() {
    todoRepositorySpringDataJPA.deleteAll();
  }
}
