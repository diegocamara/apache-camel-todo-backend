package com.todo.infrastructure.web.routes;

import com.todo.infrastructure.configuration.RestServerConfiguration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestConfigurationRoute extends RouteBuilder {

  private RestServerConfiguration restServerConfiguration;

  public RestConfigurationRoute(RestServerConfiguration restServerConfiguration) {
    this.restServerConfiguration = restServerConfiguration;
  }

  @Override
  public void configure() throws Exception {

    restConfiguration()
        .component(restServerConfiguration.getComponent())
        .port(restServerConfiguration.getPort())
        .contextPath(restServerConfiguration.getContextPath())
        .enableCORS(true)
        .bindingMode(RestBindingMode.auto);
  }
}
