package org.example;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:store.db";

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        try (Statement st = conn.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON");
        }
        return conn;
    }

    public static void init() {
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            // CUSTOMER
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS CUSTOMER (" +
                            "DNI TEXT PRIMARY KEY, " +
                            "Name TEXT NOT NULL, " +
                            "FirstSurname TEXT NOT NULL, " +
                            "LastSurname TEXT NOT NULL" +
                            ")");

            // ITEMS
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS ITEMS (" +
                            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "UnitPrice REAL NOT NULL, " +
                            "Name TEXT NOT NULL, " +
                            "Stock INTEGER NOT NULL DEFAULT 0" +
                            ")");

            // SALES
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS SALES (" +
                            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "TotalPrice REAL NOT NULL DEFAULT 0, " +
                            "SalesDate TEXT NOT NULL, " +
                            "DNI_Member TEXT, " +
                            "FOREIGN KEY (DNI_Member) REFERENCES CUSTOMER(DNI) ON UPDATE CASCADE ON DELETE SET NULL" +
                            ")");

            // ITEMS_SALES (junction)
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS ITEMS_SALES (" +
                            "ID_Item INTEGER NOT NULL, " +
                            "ID_Sale INTEGER NOT NULL, " +
                            "Quantity INTEGER NOT NULL, " +
                            "PRIMARY KEY (ID_Item, ID_Sale), " +
                            "FOREIGN KEY (ID_Item) REFERENCES ITEMS(ID) ON UPDATE CASCADE ON DELETE RESTRICT, " +
                            "FOREIGN KEY (ID_Sale) REFERENCES SALES(ID) ON UPDATE CASCADE ON DELETE CASCADE" +
                            ")");
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing database", e);
        }
    }
}
