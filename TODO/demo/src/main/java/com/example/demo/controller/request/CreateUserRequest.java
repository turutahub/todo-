
package com.example.demo.controller.request;

public class CreateUserRequest {
    private String username;
    private String password;

    public CreateUserRequest() {
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {

        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public CreateUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
