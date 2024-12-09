package com.example.mobile_invoice_management.controller;

import com.example.mobile_invoice_management.model.User;
import com.example.mobile_invoice_management.repository.IInvoiceRepository;
import com.example.mobile_invoice_management.service.IInvoiceService;
import com.example.mobile_invoice_management.service.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login_servlet")
public class LoginServlet extends HttpServlet {
    private final IInvoiceService service = new InvoiceService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = service.returnUserByUsernameAndPassword(username, password);

        if (user != null) {
            // Tạo HttpSession và lưu thông tin người dùng
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/invoice_servlet");
        } else {
            // Đăng nhập thất bại
            response.sendRedirect("index.jsp?error=true");
        }
    }
}
