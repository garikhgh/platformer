package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class GameMap {

    protected List<Player> entityList;
    public GameMap() {
        entityList = new ArrayList<>();
        entityList.add(new Player(40, 460, this));
    }
    public void render(OrthographicCamera camera, SpriteBatch spriteBatch) {
        for (Player entity: entityList) {
            entity.render(spriteBatch);
        }
    }

    public void update(float delta) {
        for (Player entity : entityList) {
            entity.update(delta, -9.8f);
        }
    }

    public abstract void dispose();

    public TileType getTileTypeByLocation(int layer, float x, float y) {
        return this.getTileTypeByCoordinate(layer, (int) TileType.TILE_SIZE, (int) y / TileType.TILE_SIZE);
    }

    public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);

    public boolean doesRectCollideWithMap(float x, float y, int width, int height) {
        if (x < 0 || y < 0 || x + width > getPixelWidth() || y + height > getPixelHeight()) {
            return true;
        }
        for (int row = (int) y / TileType.TILE_SIZE; row < Math.ceil((y + height) / TileType.TILE_SIZE); row++) {
            for (int col = (int) x / TileType.TILE_SIZE; col < Math.ceil((x + width) / TileType.TILE_SIZE); col++) {
                for (int layer = 0; layer < getLayers(); layer++) {
                    TileType type = getTileTypeByCoordinate(layer, col, row);
                    if (type != null && type.isCollidable())
                        return true;
                }
            }
        }
        return false;
    }

public int getPixelWidth() {
    return this.getWidth() * TileType.TILE_SIZE;
}

public int getPixelHeight() {
    return this.getHeight() * TileType.TILE_SIZE;
}

public abstract int getWidth();

public abstract int getHeight();

public abstract int getLayers();
}
