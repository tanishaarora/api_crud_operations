package com.example.myapplication;

public class RetroPostModel {
    private String email;
    private String password;

    public RetroPostModel(String identity, String pass) {
        this.email = identity;
        this.password  = pass;

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
