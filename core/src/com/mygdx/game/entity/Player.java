package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameMap;
import jdk.javadoc.internal.doclets.formats.html.markup.Entity;




public class Player extends AbstractEntity {

    private static final int SPEED = 50;
    private static final int JUMP_VELOCITY = 5;

    Texture image;

    public Player(float x, float y, GameMap gameMap) {
        super(x, y, EntityType.PLAYER, gameMap);
        image = new Texture("player.png");
    }

    @Override
    public void update(float deltaTime, float gravity) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            this.velocitY += JUMP_VELOCITY * getWeight();
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !grounded && this.velocitY > 0) {
            this.velocitY += JUMP_VELOCITY * getWeight() * deltaTime;
        }
        super.update(deltaTime, gravity);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveX(-SPEED * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveX(SPEED * deltaTime);
        }
    }



    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWeight(), getHeight());
    }
}
