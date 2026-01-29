package com.game.interfaces;

import com.game.model.Player;
import com.game.model.PlayerFull;
import java.sql.SQLException;

public interface IPlayerRepository {
    Player getPlayerById(int id) throws SQLException;
    void updatePlayer(Player player) throws SQLException;
    void createPlayer(Player player) throws SQLException;
    void updatePlayerWeapon(int playerId, int weaponId) throws SQLException;

    PlayerFull getFullPlayer(int id) throws SQLException;
}