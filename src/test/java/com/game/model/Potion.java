package com.game.model;

public class Potion {
    private int id;
    private String name;
    private String type;
    private int value;
    public Potion(int id, String name, String type, int value) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
}