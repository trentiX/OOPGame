package com.game.service;

import com.game.model.Enemy;
import com.game.model.Weapon;

import java.util.Random;

public class GameService {
    private Random random = new Random();

    private static GameService instance = new GameService();

    private GameService(){}

    public static GameService getInstance() {
        return instance;
    }

    public int calculateDamage(Weapon weapon, Enemy enemy) {
        int damage = 10 + (weapon != null ? weapon.getAdditionalDamage() : 0);
        if (enemy.getType().equals("SKELETON") && weapon.getType().equals("BLUNT")) return (int)(damage * 1.5);
        if (enemy.getType().equals("ZOMBIE") && weapon.getType().equals("SLASH")) return (int)(damage * 1.3);
        return damage;
    }

    public String getRandomLootType() {
        double r = random.nextDouble();
        if (r < 0.4) return "NONE";
        if (r < 0.8) return "POTION";
        return "WEAPON";
    }
}