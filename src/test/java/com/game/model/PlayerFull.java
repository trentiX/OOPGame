package com.game.model;

public class PlayerFull {
    private Player player;
    private Weapon weapon;

    public PlayerFull(Player player, Weapon weapon) {
        this.player = player;
        this.weapon = weapon;
    }

    public Player getPlayer() {
        return player;
    }

    public Weapon getWeapon() {
        return weapon ;
    }
}