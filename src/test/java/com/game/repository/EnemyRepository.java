package com.game.repository;

import com.game.model.Enemy;
import java.sql.*;

public class EnemyRepository {
    private Connection connection;
    public EnemyRepository(Connection conn) { this.connection = conn; }

    public Enemy getRandomEnemy() throws SQLException {

    }
}