package com.game.repository;

import java.sql.*;

public class PotionRepository {
    private Connection connection;
    public PotionRepository(Connection conn) { this.connection = conn; }

    public void healPlayer(int playerId, int amount) throws SQLException {

    }
}