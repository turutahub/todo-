package com.example.demo.controller.response;

import com.example.demo.model.TodoModel;

import java.util.List;
    public class TodoResponse {
        public List<TodoModel> List;

        public TodoResponse(List<TodoModel> List) {
            this.List = List;
        }
    }

