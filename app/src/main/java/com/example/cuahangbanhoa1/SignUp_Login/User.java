package com.example.cuahangbanhoa1.SignUp_Login;

public class User {
    private String name;
    private String username;
    private String password;
    private String date;

    public User() {
    }

    public User(String name, String username, String password, String date) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String email) {
        this.name = name;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
