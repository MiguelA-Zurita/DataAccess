package com.example.demo;

/*
 * Database utility class responsible for:
 * - Providing JDBC connections, enabling SQLite foreign keys and creating the schema on application startup if it does not exist.
 */

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:store.db";

    //Opens a new JDBC connection to the SQLite database and enables foreign keys.
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        try (Statement st = conn.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON");
        }
        return conn;
    }

    /*
     * Initializes the database schema and creates the following tables if missing:
     * CUSTOMER, ITEMS, SALES, and ITEMS_SALES.
     */
    public static void init() { // Method to initialize the database
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            // items
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS ITEMS (" +
                            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "Name TEXT NOT NULL, " +
                            "Description TEXT DEFAULT NULL," +
                            "UnitPrice DOUBLE NOT NULL, " +
                            "Stock INTEGER NOT NULL DEFAULT 0" +
                            ")");
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing database", e);
        }
    }
}
