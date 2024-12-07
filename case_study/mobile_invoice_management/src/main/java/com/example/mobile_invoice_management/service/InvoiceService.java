package com.example.mobile_invoice_management.service;

import com.example.mobile_invoice_management.model.Customer;
import com.example.mobile_invoice_management.model.Invoice;
import com.example.mobile_invoice_management.model.InvoiceDetail;
import com.example.mobile_invoice_management.model.Product;
import com.example.mobile_invoice_management.repository.BaseRepository;
import com.example.mobile_invoice_management.repository.IInvoiceRepository;
import com.example.mobile_invoice_management.repository.InvoiceRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class InvoiceService implements IInvoiceService {
    private final String FIND_CUSTOMER_BY_ID = "select * from customer where CustomerID = ?";
    private final String REMOVE_INVOICE_DETAIL_BY_INVOICE_ID = "delete from invoicedetail where InvoiceID=?";
    private final String REMOVE_INVOICE_BY_INVOICE_ID = "delete from invoice where InvoiceID=?";
    private final String FIND_INVOICE_BY_ID = "select * from invoice where InvoiceID = ?";
    private final String FIND_PRODUCT_BY_ID = "select * from product where ProductID = ?";

    @Override
    public Customer returnCustomerByID(int id) {
        BaseRepository baseRepository = new BaseRepository();
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int customerID = resultSet.getInt("CustomerID");
                String fullName = resultSet.getString("FullName");
                String phone = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");
                return new Customer(customerID, fullName, phone, email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void deleteInvoice(int id) {
        BaseRepository baseRepository = new BaseRepository();
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_INVOICE_DETAIL_BY_INVOICE_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Remove INVOICE DETAIL successfully");
            preparedStatement = connection.prepareStatement(REMOVE_INVOICE_BY_INVOICE_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Remove INVOICE successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<InvoiceDetail> returnInvoiceDetailList(int id) {
        IInvoiceRepository repository = new InvoiceRepository();
        return repository.toInvoiceDetailList(id);
    }

    @Override
    public Invoice returnInvoiceByID(int id) {
        BaseRepository baseRepository = new BaseRepository();
        Connection connection = baseRepository.getConnection();
        Invoice invoice = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_INVOICE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int invoiceID = resultSet.getInt("InvoiceID");
                int customerID = resultSet.getInt("CustomerID");
                LocalDateTime date = resultSet.getTimestamp("Date").toLocalDateTime();
                double amount = resultSet.getDouble("TotalAmount");
                invoice = new Invoice(invoiceID, customerID, date, amount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return invoice;
    }

    @Override
    public Product returnProductByID(int id) {
        BaseRepository baseRepository = new BaseRepository();
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                String brand = resultSet.getString("Brand");
                double price = resultSet.getDouble("Price");
                int stock = resultSet.getInt("Stock");
                return new Product(productID, productName, brand, price, stock);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
