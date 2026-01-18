package com.game.repository;

import com.game.model.Weapon;
import java.sql.*;

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
                        rs.getString("type"), rs.getInt("additional_damage"));
            }
        }
        return null;
    }
}