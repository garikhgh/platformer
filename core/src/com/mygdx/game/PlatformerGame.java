package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class PlatformerGame extends Game {
	SpriteBatch batch;
	GameMap gameMap;
	OrthographicCamera cam;
	
	@Override
	public void create () {
//		batch = new SpriteBatch();
		gameMap = new TiledGameMap();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		if (Gdx.input.isTouched()) {
			cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
			cam.update();
		}

		if (Gdx.input.justTouched()) {
			Vector3 pos = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			TileType type = gameMap.getTileTypeByLocation(1, pos.x, pos.y);
			if (type != null) {
				Gdx.app.log("You clicked to null cell", type.getName());
			}
		}
		gameMap.render(cam);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
