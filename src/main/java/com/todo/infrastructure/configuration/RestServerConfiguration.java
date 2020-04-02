package com.todo.infrastructure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "rest-server")
@Getter
@Setter
public class RestServerConfiguration {
  private static final String EMPTY = "";
  private String component;
  private int port;
  private String host;
  private String contextPath;
  private String url;
}
