# How it works

This application uses apache camel web support to provide a Todo REST API based on [Todo-Backend](https://www.todobackend.com/).

Some modules used in this app:
* [Spring](https://spring.io/) to perform dependency injection
* [Apache Camel REST DSL Component](https://camel.apache.org/manual/latest/rest-dsl.html) for REST
* [H2 Database Engine](https://www.h2database.com) to persist data

### Project Structure

```
main/
|-- java/
|------ com/
|--------- todo/
|          +-- domain -> All domain models and business logic
|          +-- infrastructure -> Technical details package
```

## Getting started

### Build local executable jar
```bash
./gradlew bootJar
```

### Running local application
```bash
java -jar build/libs/todo-backend.jar --spring.profiles.active=dev
```

The server should be running at http://localhost:8080

### Running tests

[Run localhost spec tests](https://www.todobackend.com/specs/index.html?http://localhost:8080/todos)

[Run app heroku spec tests](https://www.todobackend.com/specs/index.html?https://spring-todo-backend.herokuapp.com/todos)

## Help
Improvements are welcome, feel free to contribute.