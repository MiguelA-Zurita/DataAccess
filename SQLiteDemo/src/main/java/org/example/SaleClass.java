package org.example;

/**
 * Sale data-access class (DAO-like static API).
 *
 * Responsibilities:
 * - Defines the immutable {@link Sale} record that represents rows from the SALES table.
 * - Provides CRUD operations for sales using {@link Database} connections.
 *
 * Notes:
 * - The {@code dniMember} field is nullable and stored as NULL when empty.
 */

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleClass {

    /**
     * Immutable projection of a row in the SALES table.
     * @param id primary key (autoincrement)
     * @param totalPrice aggregated sale amount (REAL)
     * @param salesDate ISO-8601 date (stored as TEXT)
     * @param dniMember optional customer DNI (nullable)
     */
    public record Sale(int id, double totalPrice, LocalDate salesDate, String dniMember) {
        @Override
        public String toString() {
            return "Sale{" +
                    "id=" + id +
                    ", totalPrice=" + totalPrice +
                    ", salesDate=" + salesDate +
                    ", dniMember=" + (dniMember == null ? "null" : ('\"' + dniMember + '\"')) +
                    '}';
        }
    }

    /**
     * Inserts a new sale and returns the generated ID.
     * @param s sale values to insert
     * @return generated primary key, or null if not returned by the driver
     * @throws SQLException if the insert fails
     */
    public static Integer add(Sale s) throws SQLException {
        String sql = "INSERT INTO SALES(TotalPrice, SalesDate, DNI_Member) VALUES (?,?,?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, s.totalPrice());
            ps.setString(2, s.salesDate().toString());
            if (s.dniMember() == null || s.dniMember().isBlank()) {
                ps.setNull(3, Types.VARCHAR);
            }
            else {
                ps.setString(3, s.dniMember());
            }
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return null;
    }

    /**
     * Returns all sales ordered by ID.
     * @return list of sales
     * @throws SQLException if the query fails
     */
    public static List<Sale> getAll() throws SQLException {
        String sql = "SELECT ID, TotalPrice, SalesDate, DNI_Member FROM SALES ORDER BY ID";
        List<Sale> list = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapSale(rs));
            }
        }
        return list;
    }

    /**
     * Finds a sale by ID.
     * @param id sale identifier
     * @return the sale or null if not found
     * @throws SQLException if the query fails
     */
    public static Sale getById(int id) throws SQLException {
        String sql = "SELECT ID, TotalPrice, SalesDate, DNI_Member FROM SALES WHERE ID = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapSale(rs);
                }
            }
        }
        return null;
    }

    /**
     * Updates an existing sale (row selected by ID).
     * @param s values to set
     * @throws SQLException if the update fails
     */
    public static void update(Sale s) throws SQLException {
        String sql = "UPDATE SALES SET TotalPrice = ?, SalesDate = ?, DNI_Member = ? WHERE ID = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, s.totalPrice());
            ps.setString(2, s.salesDate().toString());
            if (s.dniMember() == null || s.dniMember().isBlank()) ps.setNull(3, Types.VARCHAR); else ps.setString(3, s.dniMember());
            ps.setInt(4, s.id());
            ps.executeUpdate();
        }
    }

    /**
     * Deletes a sale by ID.
     * @param id identifier
     * @throws SQLException if the delete fails
     */
    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM SALES WHERE ID = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }


    /**
     * Maps the current row of a {@link ResultSet} to a {@link Sale} record.
     */
    private static Sale mapSale(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        double totalPrice = rs.getDouble("TotalPrice");
        LocalDate date = LocalDate.parse(rs.getString("SalesDate"));
        String dni = rs.getString("DNI_Member");
        if (rs.wasNull()) dni = null;
        return new Sale(id, totalPrice, date, dni);
    }
}
