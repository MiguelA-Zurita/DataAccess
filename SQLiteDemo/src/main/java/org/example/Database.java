package org.example;

/*
 * Database utility class responsible for:
 * - Providing JDBC connections, enabling SQLite foreign keys and creating the schema on application startup if it does not exist.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
            // Customer
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS CUSTOMER (" +
                            "DNI TEXT PRIMARY KEY, " +
                            "Name TEXT NOT NULL, " +
                            "FirstSurname TEXT NOT NULL, " +
                            "LastSurname TEXT NOT NULL" +
                            ")");

            // items
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS ITEMS (" +
                            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "UnitPrice REAL NOT NULL, " +
                            "Name TEXT NOT NULL, " +
                            "Stock INTEGER NOT NULL DEFAULT 0" +
                            ")");

            // sales
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS SALES (" +
                            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "TotalPrice REAL NOT NULL DEFAULT 0, " +
                            "SalesDate TEXT NOT NULL, " +
                            "DNI_Member TEXT, " +
                            "FOREIGN KEY (DNI_Member) REFERENCES CUSTOMER(DNI) ON UPDATE CASCADE ON DELETE SET NULL" +
                            ")");

            // Items_sales
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
