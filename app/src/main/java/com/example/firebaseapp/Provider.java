package com.example.firebaseapp;

import java.sql.Array;

public class Provider {
    private String username;
    private String password;
    private String email;
    private int city;
    private int region;
    private String address;
    private String profilePicture, dni;
    private int gender;
    private int created, updated;
    private int balance, mobileNumber;
    private String description;

    public Provider(String username, String password, String email, int city, int region, String address, String profilePicture, String dni, int gender, int created, int updated, int balance, int mobileNumber, String description) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.city = city;
        this.region = region;
        this.address = address;
        this.profilePicture = profilePicture;
        this.dni = dni;
        this.gender = gender;
        this.created = created;
        this.updated = updated;
        this.balance = balance;
        this.mobileNumber = mobileNumber;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getCity() {
        return city;
    }

    public int getRegion() {
        return region;
    }

    public String getAddress() {
        return address;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getDni() {
        return dni;
    }

    public int getGender() {
        return gender;
    }

    public int getCreated() {
        return created;
    }

    public int getUpdated() {
        return updated;
    }

    public int getBalance() {
        return balance;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public String getDescription() {
        return description;
    }




}