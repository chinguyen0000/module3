package com.example.mobile_invoice_management.repository;

import com.example.mobile_invoice_management.model.Customer;
import com.example.mobile_invoice_management.model.Invoice;
import com.example.mobile_invoice_management.model.InvoiceDetail;
import com.example.mobile_invoice_management.model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InvoiceRepository implements IInvoiceRepository {
    private final String SHOW_ALL_PRODUCTS = "select * from product";
    private final String SHOW_ALL_INVOICES = "select * from invoice";
    private final String SHOW_INVOICE_DETAIL = "select * from invoicedetail where InvoiceID = ?";
    private final String SHOW_ALL_CUSTOMERS = "select * from customer";
    private final String SHOW_CUSTOMER_ID_BY_FULLNAME = "select CustomerID from customer where FullName like '% ?' or FullName = ? limit 1";


    @Override
    public List<Product> toProductList() {
        List<Product> products = new ArrayList<>();
        BaseRepository baseRepository = new BaseRepository();
        Connection connection = baseRepository.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SHOW_ALL_PRODUCTS);
            while (resultSet.next()) {
                int id = resultSet.getInt("ProductID");
                String name = resultSet.getString("ProductName");
                String brand = resultSet.getString("Brand");
                double price = resultSet.getDouble("Price");
                int stock = resultSet.getInt("Stock");
                products.add(new Product(id, name, brand, price, stock));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public List<Invoice> toInvoiceList() {
        List<Invoice> invoices = new ArrayList<>();
        BaseRepository baseRepository = new BaseRepository();
        Connection connection = baseRepository.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SHOW_ALL_INVOICES);
            while (resultSet.next()) {
                int id = resultSet.getInt("InvoiceID");
                int customerID = resultSet.getInt("CustomerID");
                LocalDateTime date = resultSet.getTimestamp("Date").toLocalDateTime();
                double amount = resultSet.getDouble("TotalAmount");
                invoices.add(new Invoice(id, customerID, date, amount));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return invoices;
    }

    @Override
    public List<Customer> toCustomerList() {
        BaseRepository base = new BaseRepository();
        Connection connection = base.getConnection();
        List<Customer> customers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SHOW_ALL_CUSTOMERS);
            while (resultSet.next()) {
                int id = resultSet.getInt("CustomerID");
                String name = resultSet.getString("FullName");
                String phone = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");
                customers.add(new Customer(id, name, phone, email));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public List<InvoiceDetail> toInvoiceDetailList(int id) {
        BaseRepository base = new BaseRepository();
        Connection connection = base.getConnection();
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_INVOICE_DETAIL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int invoiceDetailID = resultSet.getInt("InvoiceDetailID");
                int invoiceID = resultSet.getInt("InvoiceID");
                int productID = resultSet.getInt("ProductID");
                int quantity = resultSet.getInt("Quantity");
                double SubTotal = resultSet.getDouble("Subtotal");
                invoiceDetails.add(new InvoiceDetail(invoiceDetailID, invoiceID, productID, quantity, SubTotal));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return invoiceDetails;
    }

    @Override
    public int toCustomerIDByFullName(String fullName) {
        BaseRepository base = new BaseRepository();
        Connection connection = base.getConnection();
        int customerID = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_CUSTOMER_ID_BY_FULLNAME);
            preparedStatement.setString(1, fullName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerID = resultSet.getInt("CustomerID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerID;
    }

}
