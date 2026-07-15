package com.example.malabetuktuk;

public class Part {

    // Attributes
    private String partCode;
    private String partName;
    private String brand;
    private double price;
    private int quantity;
    private String category;
    private String dateAdded;
    private String imageName;

    // Constructor
    public Part(String partCode, String partName, String brand,
                double price, int quantity,
                String category, String dateAdded,
                String imageName) {

        this.partCode = partCode;
        this.partName = partName;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.dateAdded = dateAdded;
        this.imageName = imageName;
    }

    // Getters
    public String getPartCode() {
        return partCode;
    }

    public String getPartName() {
        return partName;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getImageName() {
        return imageName;
    }

    // Setters
    public void setPartName(String partName) {
        this.partName = partName;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    // Display object as text
    @Override
    public String toString() {
        return partCode + " | " +
                partName + " | " +
                brand + " | Rs." +
                price + " | Qty: " +
                quantity + " | " +
                category;
    }
}