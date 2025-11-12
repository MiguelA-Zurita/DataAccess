package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemSaleDAO {

    public static void add(int itemId, int saleId, int qty) throws SQLException {
        String sql = "INSERT INTO ITEMS_SALES(ID_Item, ID_Sale, Quantity) VALUES (?,?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, itemId);
            ps.setInt(2, saleId);
            ps.setInt(3, qty);
            ps.executeUpdate();
        }
    }

    public static void update(int itemId, int saleId, int qty) throws SQLException {
        String sql = "UPDATE ITEMS_SALES SET Quantity = ? WHERE ID_Item = ? AND ID_Sale = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, qty);
            ps.setInt(2, itemId);
            ps.setInt(3, saleId);
            ps.executeUpdate();
        }
    }

    public static void delete(int itemId, int saleId) throws SQLException {
        String sql = "DELETE FROM ITEMS_SALES WHERE ID_Item = ? AND ID_Sale = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, itemId);
            ps.setInt(2, saleId);
            ps.executeUpdate();
        }
    }

    public static List<Models.ItemInSale> listBySale(int saleId) throws SQLException {
        String sql = "SELECT isls.ID_Sale, isls.ID_Item, i.Name, i.UnitPrice, isls.Quantity " +
                "FROM ITEMS_SALES isls JOIN ITEMS i ON i.ID = isls.ID_Item WHERE isls.ID_Sale = ? ORDER BY isls.ID_Item";
        List<Models.ItemInSale> list = new ArrayList<>();
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, saleId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Models.ItemInSale(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            rs.getInt(5)
                    ));
                }
            }
        }
        return list;
    }
}
