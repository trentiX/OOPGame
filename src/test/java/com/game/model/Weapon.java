package com.game.model;

public class Weapon {
    private int id;
    private String name;
    private String type;
    private int additionalDamage;

    public Weapon(int id, String name, String type, int additionalDamage) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.additionalDamage = additionalDamage;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAdditionalDamage() {
        return additionalDamage;
    }
}