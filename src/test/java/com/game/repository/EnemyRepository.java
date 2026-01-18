package com.game.repository;

import com.game.model.Enemy;
import java.sql.*;

public class EnemyRepository {
    private Connection connection;
    public EnemyRepository(Connection conn) { this.connection = conn; }

    public Enemy getRandomEnemy() throws SQLException {
        String sql = "SELECT * FROM enemies ORDER BY RANDOM() LIMIT 1";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return new Enemy(rs.getInt("id"), rs.getString("name"), rs.getString("type"),
                        rs.getInt("hp"), rs.getInt("damage"), rs.getInt("exp_reward"));
            }
        }
        return null;
    }
}