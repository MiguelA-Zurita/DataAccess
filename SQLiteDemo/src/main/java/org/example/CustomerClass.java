package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerClass {

    public record Customer(String dni, String name, String firstSurname, String lastSurname) {//Record Customer for the builder and ToString
        @Override
        public String toString() {
            return "Customer{" +
                    "dni='" + dni + '\'' +
                    ", name='" + name + '\'' +
                    ", firstSurname='" + firstSurname + '\'' +
                    ", lastSurname='" + lastSurname + '\'' +
                    '}';
        }
    }

    /**
     * Inserts a new customer.
     */
    public static void add(Customer c) throws SQLException {
        String sql = "INSERT INTO CUSTOMER(DNI, Name, FirstSurname, LastSurname) VALUES (?,?,?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.dni());
            ps.setString(2, c.name());
            ps.setString(3, c.firstSurname());
            ps.setString(4, c.lastSurname());
            ps.executeUpdate();
        }
    }

    /**
     * Returns all customers ordered by DNI.
     */
    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT DNI, Name, FirstSurname, LastSurname FROM CUSTOMER ORDER BY DNI";
        List<Customer> list = new ArrayList<>();
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) { //Tries the connection
            while (rs.next()) {
                list.add(new Customer(
                        rs.getString("DNI"),
                        rs.getString("Name"),
                        rs.getString("FirstSurname"),
                        rs.getString("LastSurname")
                ));
            }
        }
        return list;
    }

    /**
     * Finds a customer by primary key.
     */
    public static Customer getByDni(String dni) throws SQLException {
        String sql = "SELECT DNI, Name, FirstSurname, LastSurname FROM CUSTOMER WHERE DNI = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) { //Tries the connection
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                            rs.getString("DNI"),
                            rs.getString("Name"),
                            rs.getString("FirstSurname"),
                            rs.getString("LastSurname")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Updates a customer. The DNI in the record selects the row to update.
     */
    public static void update(Customer c) throws SQLException {
        String sql = "UPDATE CUSTOMER SET Name = ?, FirstSurname = ?, LastSurname = ? WHERE DNI = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) { //Tries the connection
            ps.setString(1, c.name());
            ps.setString(2, c.firstSurname());
            ps.setString(3, c.lastSurname());
            ps.setString(4, c.dni());
            ps.executeUpdate();
        }
    }

    /**
     * Deletes a customer by DNI.
     */
    public static void delete(String dni) throws SQLException {
        String sql = "DELETE FROM CUSTOMER WHERE DNI = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {//Tries the connection
            ps.setString(1, dni);
            ps.executeUpdate();
        }
    }
}
