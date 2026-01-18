package com.game.repository;

import com.game.model.Player;

import java.sql.*;

public class PlayerRepository {
    private final Connection connection;

    public PlayerRepository(Connection connection) {
        this.connection = connection;
    }

    public void updatePlayer(Player player) throws SQLException {
        String query = "UPDATE players SET hp = ?, gold = ?, weapon_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, player.getHp());
            ps.setInt(2, player.getGold());
            ps.setInt(3, player.getWeaponId()); // Сохраняем текущее оружие
            ps.setInt(4, player.getId());
            ps.executeUpdate();
        }
    }

    public void createPlayer(Player player) throws SQLException {
        public void createPlayer(Player player) throws SQLException {
            String sql = "INSERT INTO players (id, name, hp, gold) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, player.getId());
                ps.setString(2, player.getName());
                ps.setInt(3, player.getHp());
                ps.setInt(4, player.getGold());
                ps.executeUpdate();
            }
    }

    public Player getPlayerById(int id) throws SQLException {
            String sql = "SELECT * FROM players WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new Player(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("hp"),
                            rs.getInt("gold"),
                            rs.getInt("weapon_id")
                    );
                }
            }
            return null;
    }

    public void updatePlayerWeapon(int playerId, int weaponId) throws SQLException {
            String sql = "UPDATE players SET weapon_id = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, weaponId);
                ps.setInt(2, playerId);
                ps.executeUpdate();
            }
    }
}