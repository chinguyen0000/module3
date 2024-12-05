package com.example.mobile_invoice_management.model;

import java.time.LocalDateTime;

public class Invoice {
    private int invoiceID;
    private int customerID;
    private LocalDateTime date;
    private double totalAmount;

    public Invoice(int invoiceID, int customerID, LocalDateTime date, double totalAmount) {
        this.invoiceID = invoiceID;
        this.customerID = customerID;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public Invoice(int customerID, LocalDateTime date, double totalAmount) {
        this.customerID = customerID;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
