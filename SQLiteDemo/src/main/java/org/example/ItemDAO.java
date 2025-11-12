package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public static void add(Tables.Item i) throws SQLException {
        String sql = "INSERT INTO ITEMS(UnitPrice, Name, Stock) VALUES (?,?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, i.unitPrice());
            ps.setString(2, i.name());
            ps.setInt(3, i.stock());
            ps.executeUpdate();
        }
    }

    public static List<Tables.Item> getAll() throws SQLException {
        String sql = "SELECT ID, UnitPrice, Name, Stock FROM ITEMS ORDER BY ID";
        List<Tables.Item> list = new ArrayList<>();
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Tables.Item(
                        rs.getInt("ID"),
                        rs.getDouble("UnitPrice"),
                        rs.getString("Name"),
                        rs.getInt("Stock")
                ));
            }
        }
        return list;
    }

    public static Tables.Item getById(int id) throws SQLException {
        String sql = "SELECT ID, UnitPrice, Name, Stock FROM ITEMS WHERE ID = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Tables.Item(
                            rs.getInt("ID"),
                            rs.getDouble("UnitPrice"),
                            rs.getString("Name"),
                            rs.getInt("Stock")
                    );
                }
            }
        }
        return null;
    }

    public static void update(Tables.Item i) throws SQLException {
        String sql = "UPDATE ITEMS SET UnitPrice = ?, Name = ?, Stock = ? WHERE ID = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, i.unitPrice());
            ps.setString(2, i.name());
            ps.setInt(3, i.stock());
            ps.setInt(4, i.id());
            ps.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM ITEMS WHERE ID = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
