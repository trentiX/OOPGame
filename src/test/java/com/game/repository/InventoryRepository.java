package com.game.repository;

import java.sql.*;

public class InventoryRepository {
    private Connection connection;
    public InventoryRepository(Connection conn) { this.connection = conn; }

    public void addItem(int playerId, String type, int itemId) throws SQLException {

    }

    public int countItem(int playerId, String type) throws SQLException {

    }

    public void usePotion(int playerId) throws SQLException {

    }
}