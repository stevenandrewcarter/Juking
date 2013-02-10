package com.juking.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.juking.engine.entities.Player;

/**
 *
 */
public class Engine extends Game {

  private OrthographicCamera camera;
  private SpriteBatch batch;
  private Player player;

  /**
   *
   */
  @Override
  public void create() {
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
    batch = new SpriteBatch();
    player = new Player(new Vector2(800 / 2 - 48 / 2, 20), 5.0f, new Vector2(1.0f, 1.0f), new Vector2(), new Vector2(), 1.0f, 1.0f, 0.5f);
  }

  /**
   *
   */
  @Override
  public void render() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    camera.update();
    batch.setProjectionMatrix(camera.combined);
    player.render(batch);
    if (Gdx.input.isTouched()) {
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);
      player.setDestinationX(touchPos.x - 124 / 2);
      player.setDestinationY(touchPos.y - 124 / 2);
    }
  }
}
