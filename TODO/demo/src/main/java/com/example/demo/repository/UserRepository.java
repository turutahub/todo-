package com.example.demo.repository;

import com.example.demo.datasource.LoginEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    LoginEntity findByUsernameAndPassword(String username, String password);

    LoginEntity insert(LoginEntity user);
}
