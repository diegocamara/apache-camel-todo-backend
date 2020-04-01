package com.todo.infrastructure.web.response;

import lombok.Data;

@Data
public class TodoResponse {
  private String title;
  private boolean completed;
}
