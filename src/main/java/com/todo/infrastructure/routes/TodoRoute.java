package com.todo.infrastructure.routes;

import com.todo.domain.model.Todo;
import com.todo.infrastructure.configuration.RestServerConfiguration;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import io.netty.handler.codec.http.HttpResponseStatus;

@Component
public class TodoRoute extends RouteBuilder {

  @Autowired private RestServerConfiguration restServerConfiguration;

  @Autowired private ApplicationContext applicationContext;

  @Override
  public void configure() throws Exception {
    restConfiguration()
        .component(restServerConfiguration.getComponent())
        .host(restServerConfiguration.getHost())
        .port(restServerConfiguration.getPort())
        .contextPath("/todos")
        .enableCORS(true)
        //        .corsAllowCredentials(true)
        //        .corsHeaderProperty("Access-Control-Allow-Origin", "*")
        //        .corsHeaderProperty("Access-Control-Allow-Headers", "*")
        .bindingMode(RestBindingMode.auto);

    rest()
        .get()
        .outType(Todo.class)
        .route()
        .setHeader(
            Exchange.HTTP_RESPONSE_CODE, simple(String.valueOf(HttpResponseStatus.OK.code())))
        .to("bean:todoService?method=retrieveAll")
        .endRest();
    rest()
        .post()
        .route()
        .setHeader(
            Exchange.HTTP_RESPONSE_CODE, simple(String.valueOf(HttpResponseStatus.OK.code())))
        .to("direct:createTodo");
    rest().delete().to("direct:deleteTodo");

    from("direct:retrieveTodo").process(exchange -> {});
    from("direct:createTodo").process(exchange -> {});
    from("direct:deleteTodo").process(exchange -> {});
  }
}
