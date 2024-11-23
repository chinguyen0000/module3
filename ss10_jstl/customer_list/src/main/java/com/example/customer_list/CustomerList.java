package com.example.customer_list;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="CustomerList", value="/customer_list")
public class CustomerList extends HttpServlet {
    private static final List<Customer> customerList;
    static {
        customerList = new ArrayList<>();
        customerList.add(new Customer(1,"Mai Văn Hoàn", LocalDate.parse("1983-08-20"),"Hà Nội","https://cdn.pixabay.com/photo/2023/04/21/15/42/portrait-7942151_640.jpg"));
        customerList.add(new Customer(2,"Nguyễn Văn Nam", LocalDate.parse("1983-08-21"),"Bắc Giang","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwN_0GJIlrPFVbrGBHjt8XDIKIFQr_8rrJ9A&s"));
        customerList.add(new Customer(3,"Nguyễn Thái Hòa", LocalDate.parse("1983-08-22"),"Nam Định","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQN9NVBIOk_blWPFbW7lJfwX3FNO6jMIsDdZg&s"));
        customerList.add(new Customer(4,"Trần Đăng Khoa", LocalDate.parse("1983-08-17"),"Hà Tây","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmLXDWlC6fldLxeoWS3b6ZH_jF-ysm6MXPpQ&s"));
        customerList.add(new Customer(5,"Nguyễn Đình Thi", LocalDate.parse("1983-08-19"),"Hà Nội","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfHc1fvEOCXFj3tmsKuuG6VZDz0muwlDW1wA&s"));
    }

    public void init() {
        System.out.println("init running ...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customerList", customerList);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    public void destroy() {
        System.out.println("destroy running ...");
    }
}
