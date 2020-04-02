package com.todo.infrastructure.web.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTodoRequest {
  private String title;
  private Boolean completed;
  private Long order;
}
