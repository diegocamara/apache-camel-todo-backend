package com.todo.domain.model;

import lombok.Data;

@Data
public class UpdateTodo {
  private Long id;
  private String title;
  private Boolean completed;
  private Long order;
}
