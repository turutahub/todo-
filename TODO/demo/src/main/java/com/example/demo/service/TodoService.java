package com.example.demo.service;

import com.example.demo.model.TodoModel;
import com.example.demo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//サービスクラスとコントローラークラスはリポジトリを使用してデータベースにアクセスするメソッド
@Service
public class TodoService {
    private final TodoRepository todorepository;
    public List<TodoModel> getAllTodo() {
        return todorepository.getAllTodo();
    }

    public TodoService(TodoRepository todorepository) {
        this.todorepository = todorepository;
    }
}
