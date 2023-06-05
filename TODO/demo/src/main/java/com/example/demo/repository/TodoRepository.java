package com.example.demo.repository;

import com.example.demo.model.TodoModel;

import java.util.List;

public interface TodoRepository {
    List<TodoModel> getAllTodo();
    void insertTodo(TodoModel todo);
    void updateTodo(TodoModel todo);
    void deleteTodoById(int id);
    TodoModel getTodoById(int id);
}

