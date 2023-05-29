package com.example.demo.controller.request;

import com.example.demo.model.TodoModel;
import com.example.demo.model.ValidateResult;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodoModelRequest {
    public final String title;
    public final LocalDate start;
    public final LocalDate end;
    public final boolean completed;
    public final LocalDateTime created_at;

    public ValidateResult validate() {
        // バリデーションルールを実装する

        return ValidateResult.success();
    }

    public TodoModel toTodoModel() {
        return new TodoModel(0, title, start, end, completed, created_at);
    }

    public TodoModelRequest() {
        this.title = null;
        this.start = null;
        this.end = null;
        this.completed = false;
        this.created_at = null;
    }
}