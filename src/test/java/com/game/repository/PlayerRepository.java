package com.game.repository;

import com.game.interfaces.IPlayerRepository;
import com.game.model.Player;
import com.game.model.PlayerFull;
import com.game.model.Weapon;

import java.sql.*;

public class PlayerRepository implements IPlayerRepository
{
    private final Connection connection;

    public PlayerRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void updatePlayer(Player player) throws SQLException
    {
        String query = "UPDATE players SET hp = ?, gold = ?, weapon_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setInt(1, player.getHp());
            ps.setInt(2, player.getGold());
            ps.setInt(3, player.getWeaponId());
            ps.setInt(4, player.getId());
            ps.executeUpdate();
        }
    }

    @Override
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

    @Override
    public Player getPlayerById(int id) throws SQLException
    {
        String sql = "SELECT * FROM players WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
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

    @Override
    public void updatePlayerWeapon(int playerId, int weaponId) throws SQLException {
        String sql = "UPDATE players SET weapon_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, weaponId);
            ps.setInt(2, playerId);
            ps.executeUpdate();
        }
    }

    @Override
    public PlayerFull getFullPlayer(int id) throws SQLException {
        String sql = """
            SELECT p.id, p.name, p.hp, p.gold, p.weapon_id,
                   w.id as wid, w.name as wname, w.type, w.additional_damage
            FROM players p
            JOIN weapons w ON p.weapon_id = w.id
            WHERE p.id = ?
            """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Player p = new Player(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("hp"),
                        rs.getInt("gold"),
                        rs.getInt("weapon_id")
                );
                Weapon w = new Weapon(
                        rs.getInt("wid"),
                        rs.getString("wname"),
                        rs.getString("type"),
                        rs.getInt("additional_damage")
                );

                return new PlayerFull(p, w); // Сабина, Наргиз P это Player и W это Weapon
            }
        }
        return null;
    }
}