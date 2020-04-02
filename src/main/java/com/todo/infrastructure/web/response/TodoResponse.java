package com.todo.infrastructure.web.response;

import com.todo.domain.model.Todo;

import lombok.Data;

@Data
public class TodoResponse {
  private String title;
  private Boolean completed;
  private Long order;
  private String url;

  public TodoResponse(Todo todo, String url) {
    this.title = todo.getTitle();
    this.completed = todo.isCompleted();
    this.order = todo.getOrder();
    this.url = url;
  }
}
