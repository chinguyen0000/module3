package com.example.productmanagement.controller;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.service.IProductService;
import com.example.productmanagement.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private final IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "show-add":
                System.out.println("show-add");
                resp.sendRedirect("product_service/show_add.jsp");
                break;
            case "show-edit":
                System.out.println("show-edit");
                showEditForm(req, resp);
                break;
            case "show-remove":
                System.out.println("show-remove");
                showRemoveForm(req, resp);
                break;
            default:
                showProductList(req, resp);
                break;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add-product":
                System.out.println("add-product");
                addProduct(req, resp);
                break;
            case "edit-product":
                System.out.println("edit-product");
                editProduct(req, resp);
                break;
            case "remove-product":
                System.out.println("remove-product");
                removeProduct(req, resp);
                break;
            default:
                System.out.println("Do nothing");
                break;
        }
    }

    private void showProductList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productService.findAll();
        req.setAttribute("productList", productList);
        RequestDispatcher view = req.getRequestDispatcher("product_list.jsp");
        view.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", id);
        RequestDispatcher view = req.getRequestDispatcher("product_service/show_edit.jsp");
        view.forward(req, resp);
    }

    private void showRemoveForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id")) ;
        Product product = productService.findById(id);
        req.setAttribute("id", id);
        req.setAttribute("product", product);
        RequestDispatcher view = req.getRequestDispatcher("product_service/show_remove.jsp");
        view.forward(req, resp);
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        boolean status = Boolean.parseBoolean(req.getParameter("status"));
        double price = Double.parseDouble(req.getParameter("price"));
        List<Product> productList = productService.findAll();
        productService.save(new Product(productList.size() + 1, name, status, price));
        System.out.println("Add successfully");
        showProductList(req, resp);
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        boolean status = Boolean.parseBoolean(req.getParameter("status"));
        double price = Double.parseDouble(req.getParameter("price"));
        productService.update(id, new Product(id, name, status, price));
        System.out.println("Edit successfully");
        resp.sendRedirect("products");
    }

    private void removeProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.delete(id);
        System.out.println("Remove successfully");
        resp.sendRedirect("products");
    }
}
