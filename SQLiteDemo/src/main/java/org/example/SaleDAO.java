package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    public static Integer add(Models.Sale s) throws SQLException {
        String sql = "INSERT INTO SALES(TotalPrice, SalesDate, DNI_Member) VALUES (?,?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, s.totalPrice);
            ps.setString(2, s.salesDate.toString());
            if (s.dniMember == null || s.dniMember.isBlank()) ps.setNull(3, Types.VARCHAR); else ps.setString(3, s.dniMember);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return null;
    }

    public static List<Models.Sale> getAll() throws SQLException {
        String sql = "SELECT ID, TotalPrice, SalesDate, DNI_Member FROM SALES ORDER BY ID";
        List<Models.Sale> list = new ArrayList<>();
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapSale(rs));
            }
        }
        return list;
    }

    public static Models.Sale getById(int id) throws SQLException {
        String sql = "SELECT ID, TotalPrice, SalesDate, DNI_Member FROM SALES WHERE ID = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapSale(rs);
            }
        }
        return null;
    }

    public static void update(Models.Sale s) throws SQLException {
        String sql = "UPDATE SALES SET TotalPrice = ?, SalesDate = ?, DNI_Member = ? WHERE ID = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, s.totalPrice);
            ps.setString(2, s.salesDate.toString());
            if (s.dniMember == null || s.dniMember.isBlank()) ps.setNull(3, Types.VARCHAR); else ps.setString(3, s.dniMember);
            ps.setInt(4, s.id);
            ps.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM SALES WHERE ID = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static void recalcTotal(int saleId) throws SQLException {
        String sql = "SELECT COALESCE(SUM(i.UnitPrice * isls.Quantity), 0) AS total " +
                "FROM ITEMS_SALES isls JOIN ITEMS i ON i.ID = isls.ID_Item WHERE isls.ID_Sale = ?";
        double total = 0;
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, saleId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) total = rs.getDouble("total");
            }
        }
        String upd = "UPDATE SALES SET TotalPrice = ? WHERE ID = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(upd)) {
            ps.setDouble(1, total);
            ps.setInt(2, saleId);
            ps.executeUpdate();
        }
    }

    private static Models.Sale mapSale(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        double totalPrice = rs.getDouble("TotalPrice");
        LocalDate date = LocalDate.parse(rs.getString("SalesDate"));
        String dni = rs.getString("DNI_Member");
        if (rs.wasNull()) dni = null;
        return new Models.Sale(id, totalPrice, date, dni);
    }
}
