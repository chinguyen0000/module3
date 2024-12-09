package com.example.mobile_invoice_management.controller;

import com.example.mobile_invoice_management.model.*;
import com.example.mobile_invoice_management.repository.IInvoiceRepository;
import com.example.mobile_invoice_management.repository.InvoiceRepository;
import com.example.mobile_invoice_management.service.IInvoiceService;
import com.example.mobile_invoice_management.service.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "InvoiceServlet", value = "/invoice_servlet")
public class InvoiceServlet extends HttpServlet {
    private static final IInvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Người dùng chưa đăng nhập
            resp.sendRedirect("/index.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");

        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "show-add":
                if (!"ADMIN".equals(user.getRole())) {
                    resp.sendRedirect("/invoice_service/unauthorized.jsp");
                    return;
                }
                showInvoiceAdd(req, resp);
                break;
            case "show-detail":
                showInvoiceDetail(req, resp);
                break;
            case "show-edit":
                if (!"ADMIN".equals(user.getRole())) {
                    resp.sendRedirect("/invoice_service/unauthorized.jsp");
                    return;
                }
                showInvoiceEdit(req, resp);
                break;
            case "remove-invoice":
                if (!"ADMIN".equals(user.getRole())) {
                    resp.sendRedirect("/invoice_service/unauthorized.jsp");
                    return;
                }
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
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("/index.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (!"ADMIN".equals(user.getRole())) {
            resp.sendRedirect("/invoice_service/unauthorized.jsp");
            return;
        }
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add-invoice":
                addInvoice(req, resp);
                break;
            case "edit-invoice":
                editInvoice(req, resp);
                break;
        }
    }

    public void displayInvoices(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Invoice> invoices = invoiceService.returnInvoiceList();
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
        Invoice invoice = invoiceService.returnInvoiceByID(invoiceID);
        List<Customer> customers = invoiceService.returnCustomerList();
        req.setAttribute("details", invoiceDetailLists);
        req.setAttribute("invoice", invoice);
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/invoice_service/show_edit.jsp").forward(req, resp);
    }

    public void showInvoiceAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = invoiceService.returnProductList();
        List<Customer> customers = invoiceService.returnCustomerList();
        req.setAttribute("products", products);
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/invoice_service/show_add.jsp").forward(req, resp);
    }

    public void addInvoice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        int customerID = invoiceService.returnCustomerIDByFullName(customerName);
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (customerID < 1) {
            out.println("<html><body>");
            out.println("<h1>Khách hàng " + customerName + " không tồn tại</h1>");
            out.println("<a href='/invoice_servlet'>Chuyển đến trang chủ</a>");
            out.println("</body></html>");
        } else {
            boolean isInsertedInvoice = invoiceService.insertInvoice(customerID, 0);
            if (isInsertedInvoice) {
                int productID = Integer.parseInt(req.getParameter("product"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                double price = invoiceService.returnProductByID(productID).getPrice();
                boolean isInsertedInvoiceDetail = invoiceService.insertInvoiceDetail(productID, quantity, (price * quantity));
                if (isInsertedInvoiceDetail) {
                    boolean isUpdated = invoiceService.updateInvoiceTotalAmount();
                    if (isUpdated) {
                        resp.sendRedirect("/invoice_servlet");
                    }
                }
            } else {
                out.println("<html><body>");
                out.println("<h1>Gặp lỗi khi tạo hóa đơn. Vui lòng thực hiện lại.</h1>");
                out.println("<a href='/invoice_servlet'>Chuyển đến trang chủ</a>");
                out.println("</body></html>");
            }
        }
    }

    public void editInvoice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int invoiceID = Integer.parseInt(req.getParameter("invoiceID"));
        LocalDateTime newDateTime = LocalDateTime.parse(req.getParameter("datetime"));
        int newCustomerID = Integer.parseInt(req.getParameter("newCustomerID"));
        double totalAmount = Double.parseDouble(req.getParameter("total"));
        boolean isUpdated = invoiceService.updateInvoice(invoiceID, newCustomerID, newDateTime, totalAmount);

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (isUpdated) {
            resp.sendRedirect("/invoice_servlet");
        } else {
            out.println("<html><body>");
            out.println("<h1>Gặp lỗi khi cập nhật hóa đơn. Vui lòng thực hiện lại.</h1>");
            out.println("<a href='/invoice_servlet'>Chuyển đến trang chủ</a>");
            out.println("</body></html>");
        }
    }
}
