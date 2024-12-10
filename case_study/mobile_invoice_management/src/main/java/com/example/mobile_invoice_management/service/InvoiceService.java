package com.example.mobile_invoice_management.service;

import com.example.mobile_invoice_management.model.*;
import com.example.mobile_invoice_management.repository.BaseRepository;
import com.example.mobile_invoice_management.repository.IInvoiceRepository;
import com.example.mobile_invoice_management.repository.InvoiceRepository;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class InvoiceService implements IInvoiceService {
    private final IInvoiceRepository repository = new InvoiceRepository();

    private final String FIND_CUSTOMER_BY_ID = "select * from customer where CustomerID = ?";
    private final String REMOVE_INVOICE_DETAIL_BY_INVOICE_ID = "delete from invoicedetail where InvoiceID=?";
    private final String REMOVE_INVOICE_BY_INVOICE_ID = "delete from invoice where InvoiceID=?";
    private final String FIND_INVOICE_BY_ID = "select * from invoice where InvoiceID = ?";
    private final String FIND_PRODUCT_BY_ID = "select * from product where ProductID = ?";
    private final String INSERT_INVOICE_DETAIL = "insert into InvoiceDetail(InvoiceID, ProductID, Quantity, Subtotal) values ((select InvoiceID from invoice order by Date desc limit 1),?,?,?)";
    private final String UPDATE_INVOICE_TOTAL_AMOUNT = "update Invoice set TotalAmount=(select sum(Subtotal) from InvoiceDetail where InvoiceID=?) where InvoiceID = ?";
    private final String UPDATE_INVOICE = "update Invoice set CustomerID=?, Date=?, TotalAmount=? where InvoiceID=?";

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

    @Override
    public List<Product> returnProductList() {
        return repository.toProductList();
    }

    @Override
    public List<Invoice> returnInvoiceList() {
        return repository.toInvoiceList();
    }

    @Override
    public List<Customer> returnCustomerList() {
        return repository.toCustomerList();
    }

    @Override
    public int returnCustomerIDByFullName(String fullName) {
        return repository.toCustomerIDByFullName(fullName);
    }

    @Override
    public boolean insertInvoice(int customerID, double totalAmount) {
        BaseRepository base = new BaseRepository();
        Connection connection = base.getConnection();
        boolean isInserted = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into invoice(CustomerID,TotalAmount) values(?,?)");
            preparedStatement.setInt(1, customerID);
            preparedStatement.setDouble(2, totalAmount);
            preparedStatement.executeUpdate();
            isInserted = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return isInserted;
    }

    @Override
    public boolean insertInvoiceDetail(int productID, int quantity, double subTotal) {
        BaseRepository base = new BaseRepository();
        Connection connection = base.getConnection();
        boolean isInserted = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INVOICE_DETAIL);
            preparedStatement.setInt(1, productID);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setDouble(3, subTotal);
            preparedStatement.executeUpdate();
            isInserted = true;
        } catch (SQLException e) {
            if (e.getSQLState().equals("45000")) {
                isInserted = false;
            } else {
                e.printStackTrace();
            }
        }
        return isInserted;
    }

    @Override
    public boolean updateInvoiceTotalAmount() {
        BaseRepository base = new BaseRepository();
        Connection connection = base.getConnection();
        boolean isUpdated = false;
        int invoiceID = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select InvoiceID from invoice order by InvoiceID desc limit 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                invoiceID = resultSet.getInt("InvoiceID");
            }
            preparedStatement = connection.prepareStatement(UPDATE_INVOICE_TOTAL_AMOUNT);
            preparedStatement.setInt(1, invoiceID);
            preparedStatement.setInt(2, invoiceID);
            preparedStatement.executeUpdate();
            isUpdated = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateInvoice(int invoiceID, int customerID, LocalDateTime dateTime, double totalAmount){
        BaseRepository repo = new BaseRepository();
        Connection connection = repo.getConnection();
        boolean isUpdated = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVOICE);
            preparedStatement.setInt(1, customerID);
            preparedStatement.setObject(2, dateTime);
            preparedStatement.setDouble(3, totalAmount);
            preparedStatement.setInt(4, invoiceID);
            preparedStatement.executeUpdate();
            isUpdated = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isUpdated;
    }

    @Override
    public User returnUserByUsernameAndPassword(String username, String password) {
        BaseRepository repo = new BaseRepository();
        Connection connection = repo.getConnection();
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where username = ? and password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String userName = resultSet.getString("username");
                String passWord = resultSet.getString("password");
                String role = resultSet.getString("role");
                user = new User(userID, userName, passWord, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
