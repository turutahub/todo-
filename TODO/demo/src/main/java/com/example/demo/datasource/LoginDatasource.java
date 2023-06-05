package com.example.demo.datasource;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LoginDatasource implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LoginDatasource(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LoginEntity insert(LoginEntity user) {
        String sql = "INSERT INTO Login_entity (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
        return user;
    }
    @Override
    public LoginEntity findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM Login_entity WHERE username = ? AND password = ?";
        List<Map<String, Object>> records = jdbcTemplate.queryForList(sql, username, password);

        // レコードからLoginEntityを作成
        if (!records.isEmpty()) {
            Map<String, Object> record = records.get(0);
            String retrievedUsername = (String) record.get("username");
            String retrievedPassword = (String) record.get("password");
            // レコードから必要な情報を取得し、LoginEntityを作成して返す
            return new LoginEntity(retrievedUsername, retrievedPassword);
        } else {
            return null; // レコードが見つからない場合はnullを返す
        }
    }
}

