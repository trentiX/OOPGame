package com.game.repository;

import com.game.model.Weapon;
import java.sql.*;

public class WeaponRepository {
    private Connection connection;
    public WeaponRepository(Connection conn) { this.connection = conn; }

    public Weapon getWeaponById(int id) throws SQLException {

    }
}