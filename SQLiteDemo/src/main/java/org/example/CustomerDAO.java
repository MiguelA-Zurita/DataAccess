package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public static void add(Models.Customer c) throws SQLException {
        String sql = "INSERT INTO CUSTOMER(DNI, Name, FirstSurname, LastSurname) VALUES (?,?,?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.dni);
            ps.setString(2, c.name);
            ps.setString(3, c.firstSurname);
            ps.setString(4, c.lastSurname);
            ps.executeUpdate();
        }
    }

    public static List<Models.Customer> getAll() throws SQLException {
        String sql = "SELECT DNI, Name, FirstSurname, LastSurname FROM CUSTOMER ORDER BY DNI";
        List<Models.Customer> list = new ArrayList<>();
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Models.Customer(
                        rs.getString("DNI"),
                        rs.getString("Name"),
                        rs.getString("FirstSurname"),
                        rs.getString("LastSurname")
                ));
            }
        }
        return list;
    }

    public static Models.Customer getByDni(String dni) throws SQLException {
        String sql = "SELECT DNI, Name, FirstSurname, LastSurname FROM CUSTOMER WHERE DNI = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Models.Customer(
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

    public static void update(Models.Customer c) throws SQLException {
        String sql = "UPDATE CUSTOMER SET Name = ?, FirstSurname = ?, LastSurname = ? WHERE DNI = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.name);
            ps.setString(2, c.firstSurname);
            ps.setString(3, c.lastSurname);
            ps.setString(4, c.dni);
            ps.executeUpdate();
        }
    }

    public static void delete(String dni) throws SQLException {
        String sql = "DELETE FROM CUSTOMER WHERE DNI = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            ps.executeUpdate();
        }
    }
}
