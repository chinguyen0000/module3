package com.example.mobile_invoice_management.service;

import com.example.mobile_invoice_management.model.*;

import java.time.LocalDateTime;
import java.util.List;

public interface IInvoiceService {
    Customer returnCustomerByID(int id);
    void deleteInvoice(int id);
    List<InvoiceDetail> returnInvoiceDetailList(int id);
    Invoice returnInvoiceByID(int id);
    Product returnProductByID(int id);
    List<Product> returnProductList();
    List<Invoice> returnInvoiceList();
    List<Customer> returnCustomerList();
    int returnCustomerIDByFullName(String fullName);
    boolean insertInvoice(int customerID, double totalAmount);
    boolean insertInvoiceDetail(int productID, int quantity, double subTotal);
    boolean updateInvoiceTotalAmount();
    boolean updateInvoice(int invoiceID, int customerID, LocalDateTime dateTime, double totalAmount);
    User returnUserByUsernameAndPassword(String username, String password);
}
