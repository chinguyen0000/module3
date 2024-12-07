package com.example.mobile_invoice_management.controller;

import com.example.mobile_invoice_management.model.Invoice;
import com.example.mobile_invoice_management.model.InvoiceDetail;
import com.example.mobile_invoice_management.repository.IInvoiceRepository;
import com.example.mobile_invoice_management.repository.InvoiceRepository;
import com.example.mobile_invoice_management.service.IInvoiceService;
import com.example.mobile_invoice_management.service.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InvoiceServlet", value = "/invoice_servlet")
public class InvoiceServlet extends HttpServlet {
    private static final IInvoiceRepository invoiceRepository = new InvoiceRepository();
    private static final IInvoiceService invoiceService = new InvoiceService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "show-add":
                break;
            case "show-detail":
                showInvoiceDetail(req, resp);
                break;
            case "show-edit":
                showInvoiceEdit(req, resp);
                break;
            case "remove-invoice":
                removeInvoice(req, resp);
                break;
            default:
                displayInvoices(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
    }

    public void displayInvoices(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Invoice> invoices = invoiceRepository.toInvoiceList();
        req.setAttribute("invoices", invoices);
        req.getRequestDispatcher("invoice_display.jsp").forward(req, resp);
    }

    public void removeInvoice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int invoiceID = Integer.parseInt(req.getParameter("invoiceID"));
        invoiceService.deleteInvoice(invoiceID);
        resp.sendRedirect(req.getContextPath() + "/invoice_servlet");
        System.out.println(req.getContextPath());
    }

    public void showInvoiceDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int invoiceID = Integer.parseInt(req.getParameter("invoiceID"));
        List<InvoiceDetail> invoiceDetailLists = invoiceService.returnInvoiceDetailList(invoiceID);
        Invoice invoice = invoiceService.returnInvoiceByID(invoiceID);
        req.setAttribute("details", invoiceDetailLists);
        req.setAttribute("invoice", invoice);
        req.getRequestDispatcher("/invoice_service/show_detail.jsp").forward(req, resp);
    }

    public void showInvoiceEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int invoiceID = Integer.parseInt(req.getParameter("invoiceID"));
        List<InvoiceDetail> invoiceDetailLists = invoiceService.returnInvoiceDetailList(invoiceID);
        Invoice invoice = invoiceService.returnInvoiceByID(invoiceID);req.setAttribute("details", invoiceDetailLists);
        req.setAttribute("invoice", invoice);
        req.getRequestDispatcher("/invoice_service/show_edit.jsp").forward(req, resp);
    }
}
