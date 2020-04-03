package com.todo.infrastructure.web.routes;

import com.todo.domain.model.Todo;
import com.todo.domain.model.UpdateTodo;
import com.todo.infrastructure.configuration.RestServerConfiguration;
import com.todo.infrastructure.web.request.TodoRequest;
import com.todo.infrastructure.web.request.UpdateTodoRequest;
import com.todo.infrastructure.web.response.TodoResponse;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.netty.handler.codec.http.HttpResponseStatus;

@Component
public class TodoRoute extends RouteBuilder {

  @Autowired private RestServerConfiguration restServerConfiguration;

  @Override
  public void configure() throws Exception {

    rest()
        .get()
        .route()
        .setHeader(
            Exchange.HTTP_RESPONSE_CODE, simple(String.valueOf(HttpResponseStatus.OK.code())))
        .to("bean:todoService?method=findAll")
        .process(configUrlProcessor())
        .endRest();

    rest()
        .get("/{id}")
        .route()
        .transform(header("id"))
        .setHeader(
            Exchange.HTTP_RESPONSE_CODE, simple(String.valueOf(HttpResponseStatus.OK.code())))
        .to("bean:todoService?method=find")
        .setBody(todoResponse())
        .endRest();

    rest()
        .post()
        .type(TodoRequest.class)
        .route()
        .transform()
        .body(todoRequest -> ((TodoRequest) todoRequest).toNewTodo())
        .setHeader(
            Exchange.HTTP_RESPONSE_CODE, simple(String.valueOf(HttpResponseStatus.CREATED.code())))
        .to("bean:todoService?method=create")
        .setBody(todoResponse())
        .endRest();

    rest()
        .patch("/{id}")
        .type(UpdateTodoRequest.class)
        .route()
        .process(configUpdateTodoProcessor())
        .setHeader(
            Exchange.HTTP_RESPONSE_CODE, simple(String.valueOf(HttpResponseStatus.OK.code())))
        .to("bean:todoService?method=update")
        .setBody(todoResponse())
        .endRest();

    rest()
        .delete()
        .route()
        .setHeader(
            Exchange.HTTP_RESPONSE_CODE, simple(String.valueOf(HttpResponseStatus.OK.code())))
        .to("bean:todoService?method=deleteAll")
        .endRest();

    rest()
        .delete("/{id}")
        .route()
        .transform(header("id"))
        .setHeader(
            Exchange.HTTP_RESPONSE_CODE, simple(String.valueOf(HttpResponseStatus.OK.code())))
        .to("bean:todoService?method=delete")
        .endRest();
  }

  private Function<Exchange, TodoResponse> todoResponse() {
    return exchange -> {
      Todo todo = exchange.getIn().getBody(Todo.class);
      return withUrl(todo);
    };
  }

  private Processor configUrlProcessor() {
    return exchange -> {
      Todo[] todoResult = exchange.getIn().getBody(Todo[].class);
      List<TodoResponse> result =
          Stream.of(todoResult).map(this::withUrl).collect(Collectors.toList());
      exchange.getIn().setBody(result);
    };
  }

  private Processor configUpdateTodoProcessor() {
    return exchange -> {
      UpdateTodoRequest updateTodoRequest = exchange.getIn().getBody(UpdateTodoRequest.class);
      Long id = exchange.getIn().getHeader("id", Long.class);
      UpdateTodo updateTodo = new UpdateTodo();
      updateTodo.setId(id);
      updateTodo.setTitle(updateTodoRequest.getTitle());
      updateTodo.setCompleted(updateTodoRequest.getCompleted());
      updateTodo.setOrder(updateTodoRequest.getOrder());
      exchange.getIn().setBody(updateTodo);
    };
  }

  private TodoResponse withUrl(Todo todo) {
    return new TodoResponse(
        todo, restServerConfiguration.getUrl().concat("/").concat(todo.getId().toString()));
  }
}
