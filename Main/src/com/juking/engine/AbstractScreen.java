package com.juking.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.juking.engine.entities.MovingEntity;

import java.util.List;

/**
 *
 */
public abstract class AbstractScreen implements Screen {

  // the fixed viewport dimensions (ratio: 1.6)
  public static final int GAME_VIEWPORT_WIDTH = 400, GAME_VIEWPORT_HEIGHT = 240;
  public static final int MENU_VIEWPORT_WIDTH = 800, MENU_VIEWPORT_HEIGHT = 480;

  protected final Engine engine;
  protected final Stage stage;

  private BitmapFont font;
  private SpriteBatch batch;
  private Skin skin;
  private TextureAtlas atlas;
  private Table table;
  protected List<MovingEntity> movingEntities;


  public AbstractScreen(Engine currentEngine) {
    this.engine = currentEngine;
    int width = (isGameScreen() ? GAME_VIEWPORT_WIDTH : MENU_VIEWPORT_WIDTH);
    int height = (isGameScreen() ? GAME_VIEWPORT_HEIGHT : MENU_VIEWPORT_HEIGHT);
    this.stage = new Stage(width, height, true);
  }

  /**
   * Get the list of moving entities from the engine
   *
   * @return A List of moving entities
   */
  public List<MovingEntity> getMovingEntities() {
    return movingEntities;
  }

  protected String getName() {
    return getClass().getSimpleName();
  }

  protected boolean isGameScreen() {
    return false;
  }

  public BitmapFont getFont() {
    if (font == null) {
      font = new BitmapFont();
    }
    return font;
  }

  public SpriteBatch getBatch() {
    if (batch == null) {
      batch = new SpriteBatch();
    }
    return batch;
  }

  public TextureAtlas getAtlas() {
    if (atlas == null) {
      atlas = new TextureAtlas(Gdx.files.internal("image-atlases/pages-info"));
    }
    return atlas;
  }

  protected Skin getSkin() {
    if (skin == null) {
      FileHandle skinFile = Gdx.files.internal("skin/uiskin.json");
      skin = new Skin(skinFile);
    }
    return skin;
  }

  protected Table getTable() {
    if (table == null) {
      table = new Table(getSkin());
      table.setFillParent(true);
      if (Engine.DEV_MODE) {
        table.debug();
      }
      stage.addActor(table);
    }
    return table;
  }

  @Override
  public void show() {
    Gdx.app.log(Engine.LOG, "Showing screen: " + getName());
    // set the stage as the input processor
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void resize(int width, int height) {
    Gdx.app.log(Engine.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height);
    // resize the stage
    stage.setViewport(width, height, true);
  }

  @Override
  public void render(float delta) {
    // Update the actors
    stage.act(delta);
    // Draw the result
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.draw();
    // draw the table debug lines
    Table.drawDebug(stage);
  }

  @Override
  public void hide() {
    Gdx.app.log(Engine.LOG, "Hiding screen: " + getName());
    // dispose the screen when leaving the screen;
    // note that the dipose() method is not called automatically by the
    // framework, so we must figure out when it's appropriate to call it
    dispose();
  }

  @Override
  public void pause() {
    Gdx.app.log(Engine.LOG, "Pausing screen: " + getName());
  }

  @Override
  public void resume() {
    Gdx.app.log(Engine.LOG, "Resuming screen: " + getName());
  }

  @Override
  public void dispose() {
    Gdx.app.log(Engine.LOG, "Disposing screen: " + getName());
    // stage.dispose();
    // as the collaborators are lazily loaded, they may be null
    if (font != null) font.dispose();
    if (batch != null) batch.dispose();
    if (skin != null) skin.dispose();
    if (atlas != null) atlas.dispose();
  }
}
