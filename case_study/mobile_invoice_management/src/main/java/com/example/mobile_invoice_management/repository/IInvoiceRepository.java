package com.example.mobile_invoice_management.repository;

import com.example.mobile_invoice_management.model.Invoice;
import com.example.mobile_invoice_management.model.InvoiceDetail;
import com.example.mobile_invoice_management.model.Product;

import java.util.List;

public interface IInvoiceRepository {
    List<Product> toProductList();
    List<Invoice> toInvoiceList();
    List<InvoiceDetail> toInvoiceDetailList(int id);
}
