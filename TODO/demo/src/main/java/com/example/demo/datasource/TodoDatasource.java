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
import java.util.stream.Collectors;

@Repository
public class TodoDatasource implements TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TodoDatasource(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TodoModel> getAllTodo() {
        String sql = "SELECT * FROM todo";
        List<Map<String, Object>> records = jdbcTemplate.queryForList(sql);
        List<TodoModel> todoList = records.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
        return todoList;
    }

    private TodoModel toModel(Map<String, Object> record) {
            if (record != null) {
                Date start = (Date) record.get("start");
                Date endDate = (Date) record.get("end_date");
                Timestamp createdAt = (Timestamp) record.get("created_at");

                return new TodoModel(
                        (int) record.get("id"),
                        (String) record.get("title"),
                        start != null ? start.toLocalDate() : null,
                        endDate != null ? endDate.toLocalDate() : null,
                        record.get("completed") != null ? (boolean) record.get("completed") : false,
                        createdAt != null ? createdAt.toLocalDateTime() : null
                        //recordがnullでないことをチェックしてから変数を取得し、nullである場合には適切な初期値を設定
                );
            } else {
                return null;
            }
        }
    @Override
    public TodoModel getTodoById(Long id) {
        String sql = "SELECT * FROM todo WHERE id = ?";
        Map<String, Object> record = jdbcTemplate.queryForMap(sql, id);

        if (record != null) {
            Date start = (Date) record.get("start");
            Date end = (Date) record.get("end_date");
            Timestamp createdAt = (Timestamp) record.get("created_at");

            return new TodoModel(
                    (int) record.get("id"),
                    (String) record.get("title"),
                    start.toLocalDate(),
                    end.toLocalDate(),
                    (boolean) record.get("completed"),
                    createdAt.toLocalDateTime()
            );
        } else {
            return null; // 指定されたIDのTodoが見つからなかった場合はnullを返す
        }
    }

    @Override
    public void insertTodo(TodoModel todo) {
        String sql = "INSERT INTO todo (title, start, end_date, completed) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                todo.getTitle(),
                Date.valueOf(todo.getStart()),
                Date.valueOf(todo.getEnd_date()),
                todo.isCompleted()
        );
    }

    @Override
    public void updateTodo(TodoModel todo) {
        String sql = "UPDATE todo SET title = ?, start = ?, end_date = ?, completed = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                todo.getTitle(),
                Date.valueOf(todo.getStart()),
                Date.valueOf(todo.getEnd_date()),
                todo.isCompleted(),
                todo.getId()
        );
    }

    @Override
    public void deleteTodoById(Long id) {
        String sql = "DELETE FROM todo WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}