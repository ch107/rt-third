package org.rothurtech.httpservletCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//DAO -> Data Access Object. It’s a design pattern used to separate database logic from business logic in your application.
public class OrderDAO {
    private Connection getConnection() throws SQLException {
        return DBConnectionPool.getDataSource().getConnection();
    }

    public void createOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (id, amount, item) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, order.getId());
            stmt.setDouble(2, order.getAmount());
            stmt.setString(3, order.getItem());
            stmt.executeUpdate();
        }
    }

    public Order getOrder(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Order(rs.getInt("id"), rs.getString("item"), rs.getDouble("amount"));
            }
        }
        return null;
    }

    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE orders SET amount = ?, item = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, order.getAmount());
            stmt.setString(2, order.getItem());
            stmt.setInt(3, order.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteOrder(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
