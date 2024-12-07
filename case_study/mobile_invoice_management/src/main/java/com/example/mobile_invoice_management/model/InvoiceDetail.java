package com.example.mobile_invoice_management.model;

import com.example.mobile_invoice_management.service.IInvoiceService;
import com.example.mobile_invoice_management.service.InvoiceService;

public class InvoiceDetail {
    private int invoiceDetailID;
    private int invoiceID;
    private int productID;
    private int quantity;
    private double subTotal;

    public InvoiceDetail(int invoiceDetailID, int invoiceID, int productID, int quantity, double subTotal) {
        this.invoiceDetailID = invoiceDetailID;
        this.invoiceID = invoiceID;
        this.productID = productID;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public InvoiceDetail(int invoiceID, int productID, int quantity, double subTotal) {
        this.invoiceID = invoiceID;
        this.productID = productID;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public int getInvoiceDetailID() {
        return invoiceDetailID;
    }

    public void setInvoiceDetailID(int invoiceDetailID) {
        this.invoiceDetailID = invoiceDetailID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Product getProductByID(int productID) {
        IInvoiceService service = new InvoiceService();
        return service.returnProductByID(productID);
    }
}
