package com.example.mobile_invoice_management;

import com.example.mobile_invoice_management.model.Product;
import com.example.mobile_invoice_management.repository.IInvoiceRepository;
import com.example.mobile_invoice_management.repository.InvoiceRepository;

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private static final IInvoiceRepository invoiceRepository = new InvoiceRepository();

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        List<Product> products = invoiceRepository.toProductList();
        for (Product product : products) {
            System.out.println(product.getProductName());
        }
        String action = request.getParameter("action");
        String invoiceID = request.getParameter("invoiceID");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + invoiceID + "</h1>");
        out.println("<h2>" + action + "</h2>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}