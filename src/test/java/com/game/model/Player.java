package com.game.model;

public class Player {
    private int id;
    private String name;
    private int hp;
    private int gold;
    private int weaponId;
    private int enemiesKilled;
    private int chestsOpened;
    private int potionsUsed;



    public Player(int id, String name, int hp, int gold, int weaponId) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.gold = gold;
        this.weaponId = weaponId;
    }

    public int getWeaponId() {return weaponId; }
    public void setWeaponId(int weaponId) { this.weaponId = weaponId; }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }
    public int getGold() { return gold; }
    public void setGold(int gold) { this.gold = gold; }
    public int getEnemiesKilled() { return enemiesKilled; }
    public void setEnemiesKilled(int enemiesKilled) { this.enemiesKilled = enemiesKilled; }

    public int getChestsOpened() { return chestsOpened; }
    public void setChestsOpened(int chestsOpened) { this.chestsOpened = chestsOpened; }

    public int getPotionsUsed() { return potionsUsed; }
    public void setPotionsUsed(int potionsUsed) { this.potionsUsed = potionsUsed; }

}
