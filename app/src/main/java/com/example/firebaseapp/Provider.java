package com.example.firebaseapp;

import java.lang.reflect.Array;

public class Provider {
    public String id;
    public String username, email, phone, country, postalCode, profilePicture, dni ,gender, created, updated, description;
    public int balance;
    public Provider() {

    }

    public Provider(String id, String username, String email, String phone, String country, String postalCode){
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.postalCode = postalCode;
        this.profilePicture = this.dni = this.gender = this.created = this.updated = description = "";
        this.balance = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}