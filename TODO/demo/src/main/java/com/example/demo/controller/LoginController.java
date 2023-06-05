package com.example.demo.controller;

import com.example.demo.controller.request.LoginRequest;
import com.example.demo.datasource.LoginEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
       String username = loginRequest.getUsername();
       String password = loginRequest.getPassword();

        // ユーザーをデータベースから検索
        LoginEntity user = userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
            // ログイン成功
            return "ログイン成功";
        } else {
            // ログイン失敗
            return "ログイン失敗";
        }
    }
}