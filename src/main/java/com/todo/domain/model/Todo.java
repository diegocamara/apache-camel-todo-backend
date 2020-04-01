package com.todo.domain.model;

import lombok.Data;

@Data
public class Todo {
  private String title;
  private boolean completed;
  private int order;
}
