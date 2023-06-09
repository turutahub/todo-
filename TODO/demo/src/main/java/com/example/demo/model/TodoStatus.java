package com.example.demo.model;

import java.util.stream.Stream;

public enum TodoStatus {
    PENDING,
    COMPLETED,
    CANCELLED;

    public static final TodoStatus INCOMPLETE = PENDING;

    public static boolean isValid(String text) {
        return Stream.of(values())
                .map(TodoStatus::name)
                .anyMatch(name -> name.equalsIgnoreCase(text));
    }
}