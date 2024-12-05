package com.example.mobile_invoice_management.model;

public class Product {
    private int productID;
    private String productName;
    private String brand;
    private double price;
    private int stock;

    public Product(int productID, String productName, String brand, double price, int stock) {
        this.productID = productID;
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public Product(String productName, String brand, double price, int stock) {
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
