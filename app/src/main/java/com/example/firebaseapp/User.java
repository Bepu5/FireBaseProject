package com.example.firebaseapp;

public class User {
    public String username, email, profile_picture;

    public User(){

    }

    public User(String username, String email, String profilePicture){
        this.username = username;
        this.email = email;
        this.profile_picture = profilePicture;
    }
}
