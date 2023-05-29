package com.example.demo.controller.response;

import com.example.demo.model.TodoModel;

import java.util.List;

public class TodoResponse {
    public List<TodoModel> todoList;

    public TodoResponse(List<TodoModel> todoList) {
        this.todoList = todoList;
    }
}
