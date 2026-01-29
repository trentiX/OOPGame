package com.game.repository;

import java.sql.*;

public class InventoryRepository {
    private Connection connection;
    public InventoryRepository(Connection conn) { this.connection = conn; }

    public void addItem(int playerId, String type, int itemId) throws SQLException {
        String sql = "INSERT INTO inventory (player_id, item_type, item_id, quantity) VALUES (?, ?, ?, 1)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, playerId);
            ps.setString(2, type);
            ps.setInt(3, itemId);
            ps.executeUpdate();
        }
    }

    public int countItem(int playerId, String type) throws SQLException {
        String sql = "SELECT SUM(quantity) FROM inventory WHERE player_id = ? AND item_type = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, playerId);
            ps.setString(2, type);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }


    public void usePotion(int playerId) throws SQLException {
        public void usePotion ( int playerId) throws SQLException {
            String sql = "UPDATE inventory\n" +
                    "SET quantity = quantity - 1\n" +
                    "WHERE id = (\n" +
                    "    SELECT id FROM inventory\n" +
                    "    WHERE player_id = ?\n" +
                    "      AND item_type = 'POTION'\n" +
                    "      AND quantity > 0\n" +
                    "    LIMIT 1\n" +
                    ");\n";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, playerId);
                ps.executeUpdate();
            }
        }
    }