package com.example.demo.controller;

import com.example.demo.datasource.LoginEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/usercreate")
    public String user(@RequestParam("username") String username, @RequestParam("password") String password) {
        LoginEntity user = new LoginEntity(username, password);
        userRepository.insert(user);

        return "ユーザーが正常に追加されました";
    }
}