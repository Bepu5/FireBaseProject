package com.example.firebaseapp;

public class User {
    public String id;
    public String username, email;

    public User(){

    }

    public User(String id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
