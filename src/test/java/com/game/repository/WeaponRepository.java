package com.game.repository;

import com.game.model.Weapon;
import java.sql.*;
import java.util.ArrayList; // Добавлено для списка
import java.util.List;      // Добавлено для списка

public class WeaponRepository {
    private Connection connection;
    public WeaponRepository(Connection conn) { this.connection = conn; }

    public Weapon getWeaponById(int id) throws SQLException {
        String sql = "SELECT * FROM weapons WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Weapon(rs.getInt("id"), rs.getString("name"),
                        rs.getString("type"), rs.getInt("additional_damage"),
                        rs.getInt("price"));
            }
        }
        return null;
    }

    public List<Weapon> getAllWeapons() throws SQLException {
        List<Weapon> weapons = new ArrayList<>();
        String sql = "SELECT * FROM weapons ORDER BY price ASC";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                weapons.add(new Weapon(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("additional_damage"),
                        rs.getInt("price")
                ));
            }
        }
        return weapons;
    }
}