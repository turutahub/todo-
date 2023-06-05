package com.example.demo.datasource;

import com.example.demo.model.TodoModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodoEntity {
    private int id;
    private String title;
    private LocalDate start;
    private LocalDate endDate;
    private boolean completed;
    private LocalDateTime created_at;

    public TodoEntity(int id, String title, LocalDate start, LocalDate endDate, boolean completed, LocalDateTime created_at) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.endDate = endDate;
        this.completed = completed;
        this.created_at = created_at;
    }

    public static TodoEntity fromModel(TodoModel todo) {
        return new TodoEntity(
                todo.getId(),
                todo.getTitle(),
                todo.getStart(),
                todo.getEnd_date(),
                todo.isCompleted(),
                todo.getCreated_at()
        );
    }

    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {

        this.start = start;
    }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}