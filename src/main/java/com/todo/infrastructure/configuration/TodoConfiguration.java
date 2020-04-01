package com.todo.infrastructure.configuration;

import com.todo.domain.repository.TodoRepository;
import com.todo.domain.service.TodoService;
import com.todo.domain.service.impl.TodoServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoConfiguration {

  @Bean
  public TodoService todoService(TodoRepository todoRepository) {
    return new TodoServiceImpl(todoRepository);
  }
}
