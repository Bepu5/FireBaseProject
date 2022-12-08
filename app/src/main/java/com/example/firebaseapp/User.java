package com.example.firebaseapp;

public class User {
    public String id;
    public String username, email, profile_picture;

    public User(){

    }

    public User(String id, String username, String email, String profilePicture){
        this.id = id;
        this.username = username;
        this.email = email;
        this.profile_picture = profilePicture;
    }
}
