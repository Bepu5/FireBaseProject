package com.example.firebaseapp;

public class TypeProvider {
    public String providerID;
    public int price;
    public String additionalComments;

    public TypeProvider(String providerID, int price, String additionalComments) {
        this.providerID = providerID;
        this.price = price;
        this.additionalComments = additionalComments;
    }

    public String getProviderID() {
        return providerID;
    }

    public int getPrice() {
        return price;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }
}