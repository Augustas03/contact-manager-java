package com.augustas.addressbook.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConnectionManager {

    private static DBConnectionManager instance;

    //Database properties
    private String url;
    private String username;
    private String password;


    private DBConnectionManager(){
        
        loadProperties();
    }

    // Get the singleton instance
    public static synchronized DBConnectionManager getInstance() {
        if (instance == null) {
            instance = new DBConnectionManager();
        }
        return instance;
    }

    private void loadProperties() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("database.properties")) {
            props.load(fis);
            this.url = props.getProperty("db.url");
            this.username = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
        } catch (IOException e) {
            System.err.println("Failed to load database properties: " + e.getMessage());
            // Fallback to default values or throw exception
            this.url = "jdbc:postgresql://localhost:5432/contacts";
            this.username = "postgres";
            this.password = "password";
        }
    }

    // Get a connection to the database
    public Connection getConnection() throws SQLException {
        try {
            // Ensure the PostgreSQL driver is loaded
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC driver not found", e);
        }
    }

    // Close resources (Connection, Statement, ResultSet)
    public void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    System.err.println("Error closing resource: " + e.getMessage());
                }
            }
        }
    }
}
