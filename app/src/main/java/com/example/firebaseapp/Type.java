package com.example.firebaseapp;

public class Type {
    private String typeName;
    private String description;

    public Type(String typeName, String description) {
        this.typeName = typeName;
        this.description = description;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getDescription() {
        return description;
    }
}