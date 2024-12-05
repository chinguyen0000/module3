package com.example.mobile_invoice_management.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Kết nối DB
public class BaseRepository {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/invoice_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public BaseRepository() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect successfully");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
