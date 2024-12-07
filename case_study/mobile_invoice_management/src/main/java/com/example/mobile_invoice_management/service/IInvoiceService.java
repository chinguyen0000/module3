package com.example.mobile_invoice_management.service;

import com.example.mobile_invoice_management.model.Customer;
import com.example.mobile_invoice_management.model.Invoice;
import com.example.mobile_invoice_management.model.InvoiceDetail;
import com.example.mobile_invoice_management.model.Product;

import java.util.List;

public interface IInvoiceService {
    Customer returnCustomerByID(int id);
    void deleteInvoice(int id);
    List<InvoiceDetail> returnInvoiceDetailList(int id);
    Invoice returnInvoiceByID(int id);
    Product returnProductByID(int id);
}
