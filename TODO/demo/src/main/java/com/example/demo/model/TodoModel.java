package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodoModel {

        public final int id;
        public final String title;
        public final LocalDate start;
        public final LocalDate end;
        public final boolean completed;
        public final LocalDateTime created_at;
        public final LocalDateTime updated;

      /* public static TodoModel empty() {
            return new TodoModel(
                    0,
                    "",
                    LocalDate.now(),
                    LocalDate.now(),
                    boolean.False,
                    LocalDateTime.now(), LocalDateTime.now());
        }*/

        public TodoModel(int id, String title, LocalDate start, LocalDate end, boolean completed, LocalDateTime created_at, LocalDateTime updated) {
            this.id = id;
            this.title = title;
            this.start = start;
            this.end = end;
            this.completed = completed;
            this.created_at = created_at;
            this.updated = updated;
        }
}

