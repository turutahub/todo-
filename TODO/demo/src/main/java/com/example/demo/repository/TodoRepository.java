package com.example.demo.repository;

import com.example.demo.model.TodoModel;

import java.util.List;

public interface TodoRepository {
    List<TodoModel> getAllTodo();

}
