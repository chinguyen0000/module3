package com.example.customer_list;

import java.time.LocalDate;

public class Customer {
    private int customer_id;
    private String full_name;
    private LocalDate date_of_birth;
    private String address;
    private String image;

    public Customer(int customer_id, String full_name, LocalDate date_of_birth, String address, String image) {
        this.customer_id = customer_id;
        this.full_name = full_name;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.image = image;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
