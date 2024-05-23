package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PlatformerGame extends Game {
	SpriteBatch batch;
	GameMap gameMap;
	OrthographicCamera cam;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameMap = new CustomGameMap(); //new TiledGameMap(); //new CustomGameMap();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		cam.update();
		gameMap.update(Gdx.graphics.getDeltaTime());
		gameMap.render(cam, batch);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gameMap.dispose();
	}
}
