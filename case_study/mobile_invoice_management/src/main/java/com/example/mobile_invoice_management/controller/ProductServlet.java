package com.example.mobile_invoice_management.controller;

import com.example.mobile_invoice_management.model.Invoice;
import com.example.mobile_invoice_management.model.Product;
import com.example.mobile_invoice_management.service.IInvoiceService;
import com.example.mobile_invoice_management.service.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/product_servlet")
public class ProductServlet extends HttpServlet {
    private final IInvoiceService service = new InvoiceService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = service.returnProductList();
        req.setAttribute("products", products);
        req.getRequestDispatcher("product_display.jsp").forward(req, resp);
    }



}
