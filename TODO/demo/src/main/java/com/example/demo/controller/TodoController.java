package com.example.demo.controller;

import com.example.demo.controller.response.TodoResponse;
import com.example.demo.model.TodoModel;
import com.example.demo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public TodoResponse getAllTodo() {
        List<TodoModel> todoList = service.getAllTodo();
        return new TodoResponse(todoList);
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTodo(@RequestBody TodoModel todo) {

        service.createTodo(todo);
        // created_atフィールドに現在の日時を設定
        todo.setCreated_at(LocalDateTime.now());
    }

    @PutMapping("/{id}")
    public void updateTodo(@PathVariable Long id, @RequestBody TodoModel updatedTodo) {
        service.updateTodo(id, updatedTodo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long id) {
        service.deleteTodoById(id);
    }
}