package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class TodoModel {
    private int id;
    private String title;
    private LocalDate start;
    private LocalDate end_date;
    private boolean completed;
    private LocalDateTime created_at;

    public TodoModel() {
    }

    public TodoModel(int id, String title, LocalDate start, LocalDate end_date, boolean completed, LocalDateTime created_at) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end_date = end_date;
        this.completed = completed;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

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

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
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
