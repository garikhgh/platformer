package com.mygdx.game.entity;

public enum EntityType {
    PLAYER("player", 14, 32, 40);

    private final String id;
    private final int width;
    private final int height;
    private final float weight;

    EntityType(String id, int width, int height, float weight) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }
}
