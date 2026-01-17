package com.game.repository;

import com.game.model.Player;

import java.sql.*;

public class PlayerRepository {
    private final Connection connection;

    public PlayerRepository(Connection connection) {
        this.connection = connection;
    }

    public void updatePlayer(Player player) throws SQLException {

    }

    public void createPlayer(Player player) throws SQLException {

    }

    public Player getPlayerById(int id) throws SQLException {

    }

    public void updatePlayerWeapon(int playerId, int weaponId) throws SQLException {

    }
}