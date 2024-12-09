package com.example.mobile_invoice_management.repository;

import com.example.mobile_invoice_management.model.Customer;
import com.example.mobile_invoice_management.model.Invoice;
import com.example.mobile_invoice_management.model.InvoiceDetail;
import com.example.mobile_invoice_management.model.Product;

import java.util.List;

public interface IInvoiceRepository {
    List<Product> toProductList();
    List<Invoice> toInvoiceList();
    List<Customer> toCustomerList();
    List<InvoiceDetail> toInvoiceDetailList(int id);
    int toCustomerIDByFullName(String fullName);


}
