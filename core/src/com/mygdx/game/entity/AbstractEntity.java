package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameMap;

public abstract class AbstractEntity {
    protected Vector2 pos;
    protected EntityType type;
    protected float velocitY = 0;
    protected GameMap gameMap;
    protected boolean grounded = false;

    public AbstractEntity(float x, float y, EntityType type,  GameMap gameMap) {
        this.pos = new Vector2(x, y);
        this.type = type;
        this.gameMap = gameMap;
    }
    public void update(float deltaTime, float gravity) {
        float newY = pos.y;

        this.velocitY += gravity * deltaTime * getWeight();
        newY += this.velocitY * deltaTime;
        if (gameMap.doesRectCollideWithMap(pos.x, newY, (int) getWith(), getHeight())) {
            if (velocitY < 0) {
                this.pos.y = (float) Math.floor(pos.y);
                grounded = true;
            }
            this.velocitY = 0;
        } else {
            this.pos.y = newY;
            grounded = false;
        }
    }
    public abstract void render(SpriteBatch batch);

    protected void  moveX(float amount) {
        float newX = pos.x + amount;
        if (!gameMap.doesRectCollideWithMap(newX, pos.y, (int) getWith(), getHeight())) {
            this.pos.x = newX;
        }
    }

    public Vector2 getPos() {
        return pos;
    }

    public EntityType getType() {
        return type;
    }

    public float getX() {
        return pos.x;
    }
    public float getY() {
        return pos.y;
    }


    public boolean isGrounded() {
        return grounded;
    }
    public float getWith() {
        return type.getWeight();
    }
    public int getHeight() {
        return type.getHeight();
    }
    public float getWeight() {
        return type.getWeight();
    }

}
