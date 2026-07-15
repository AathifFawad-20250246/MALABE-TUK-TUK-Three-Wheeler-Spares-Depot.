package com.example.malabetuktuk;

public class Dealer {

    // Attributes
    private String dealerId;
    private String dealerName;
    private String phoneNumber;
    private String location;

    // Constructor
    public Dealer(String dealerId, String dealerName, String phoneNumber, String location) {
        this.dealerId = dealerId;
        this.dealerName = dealerName;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    // Getters
    public String getDealerId() {
        return dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    // Setters
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Display dealer details
    @Override
    public String toString() {
        return dealerId + " | " +
                dealerName + " | " +
                phoneNumber + " | " +
                location;
    }
}
