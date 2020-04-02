package com.todo.infrastructure.web.request;

import com.todo.domain.model.NewTodo;

import lombok.Data;

@Data
public class TodoRequest {
  private String title;
  private Long order;

  public NewTodo toNewTodo() {
    NewTodo newTodo = new NewTodo();
    newTodo.setTitle(this.title);
    newTodo.setOrder(this.order);
    return newTodo;
  }
}
