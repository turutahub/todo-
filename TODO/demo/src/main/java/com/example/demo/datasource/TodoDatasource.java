package com.example.demo.datasource;

import com.example.demo.model.TodoModel;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

    @Repository
    public class TodoDatasource implements TodoRepository {
        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<TodoModel> getAllTodo() {
            String sql = "SELECT * FROM example_Todo";
            List<Map<String, Object>> records = jdbcTemplate.queryForList(sql);
            return records.stream()
                    .map(this::toModel)
                    .collect(toList());
        }

        private TodoModel toModel(Map<String, Object> record) {
            Date start = (Date) record.get("start");
            Date end = (Date) record.get("end");
            Timestamp created_at = (Timestamp) record.get("created_at");
            Timestamp updated = (Timestamp) record.get("updated");
            return new TodoModel(
                    (int) record.get("id"),
                    (String) record.get("title"),
                    start.toLocalDate(),
                    end.toLocalDate(),
                    (boolean) record.get("completed"),
                    created_at.toLocalDateTime(),
                    updated.toLocalDateTime()
            );
        }
    }



