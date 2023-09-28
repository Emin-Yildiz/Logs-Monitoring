package com.example.client.domain;

import java.util.UUID;

public class User {

    private UUID id;
    private String userName;
    private String email;
    private String password;

    public User (UUID id, String userName, String email, String password){
        this.email = email;
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public User(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
