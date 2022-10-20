package com.example.firebaseapp;

public class ProviderType {
    public String typeID;
    public int price;
    public String additionalComments;

    public ProviderType(String typeID, int price, String additionalComments) {
        this.typeID = typeID;
        this.price = price;
        this.additionalComments = additionalComments;
    }

    public String getTypeID() {
        return typeID;
    }

    public int getPrice() {
        return price;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }
}
