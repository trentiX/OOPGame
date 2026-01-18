package com.game.model;

public class Enemy {
    private int id;
    private String name;
    private String type;
    private int hp;
    private int damage;
    private int expReward;

    public Enemy(int id, String name, String type, int hp, int damage, int expReward) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.damage = damage;
        this.expReward = expReward;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }
    public int getDamage() { return damage; }
    public int getExpReward() { return expReward; }
}