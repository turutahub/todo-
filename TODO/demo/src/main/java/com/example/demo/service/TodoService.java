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

    public void updateTodo(Long id, TodoModel updatedTodo) {
        TodoModel todo = todoRepository.getTodoById(id);
        if (todo != null) {
            todoRepository.updateTodo(updatedTodo);
        }
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteTodoById(id);
    }
}

    /*ソート機能処理
        public List<Todo> getAllTodosSorted(String sortBy, boolean ascending) {
            Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            return todoRepository.findAll(sort);
        }
    }*/
