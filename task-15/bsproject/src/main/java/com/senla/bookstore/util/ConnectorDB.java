package com.senla.bookstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("bookstoredb");
        String url = resource.getString("db.url");
        String username = resource.getString("db.username");
        String password = resource.getString("db.password");

        return DriverManager.getConnection(url, username, password);
    }
}
