package com.example.demo.service;

import com.example.demo.model.TodoModel;
import com.example.demo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoModel> getAllTodo() {
        return todoRepository.getAllTodo();
    }

    public void createTodo(TodoModel todo) {
        todoRepository.insertTodo(todo);
    }

    public void updateTodo(int id, TodoModel updatedTodo) {
        TodoModel todo = todoRepository.getTodoById(id);
        if (todo != null) {
            todoRepository.updateTodo(updatedTodo);
        }
    }
    public void deleteTodoById(int id) {
        todoRepository.deleteTodoById(id);
    }
}