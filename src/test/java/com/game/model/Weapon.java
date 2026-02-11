package com.game.model;

public class Weapon {
    private int id;
    private String name;
    private String type;
    private int additionalDamage;
    private int price;


    public Weapon(int id, String name, String type, int additionalDamage, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.additionalDamage = additionalDamage;
        this.price = price;
    }


    public int getPrice() {
        return price;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getAdditionalDamage() { return additionalDamage; }
}