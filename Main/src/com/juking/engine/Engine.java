package com.juking.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.juking.engine.screens.SplashScreen;

/**
 *
 */
public class Engine extends Game {

  //region Public Static Variables
  public static final String LOG = Engine.class.getSimpleName();
  // whether we are in development mode
  public static final boolean DEV_MODE = false;
  //endregion

  //region Private Variables
  // Helper class for logging the current FPS
  private FPSLogger fpsLogger;
  //endregion

  //region Public Methods

  /**
   * Creates the engine. This is an override from the libgdx
   */
  @Override
  public void create() {
    Gdx.app.log(Engine.LOG, "Initialising new game");
    fpsLogger = new FPSLogger();
  }

  @Override
  public void resize(int width, int height) {
    super.resize(width, height);
    Gdx.app.log(Engine.LOG, "Resizing to: " + width + ", " + height);
    // show the splash screen when the game is resized for the first time;
    // this approach avoids calling the screen's resize method repeatedly
    if (getScreen() == null) {
      if (DEV_MODE) {
        // setScreen( new LevelScreen( this, 0 ) );
      } else {
        setScreen(new SplashScreen(this));
      }
    }
  }

  /**
   * Renders the engine. This is an override from the libgdx
   */
  @Override
  public void render() {
    super.render();
    fpsLogger.log();
    // output the current FPS
    if (DEV_MODE) fpsLogger.log();
  }

  @Override
  public void pause() {
    Gdx.app.log(Engine.LOG, "Pausing game");
    super.pause();
  }

  @Override
  public void resume() {
    Gdx.app.log(Engine.LOG, "Resuming game");
    super.resume();
  }

  @Override
  public void dispose() {
    super.dispose();
    Gdx.app.log(Engine.LOG, "Disposing game");
  }

  @Override
  public void setScreen(Screen screen) {
    super.setScreen(screen);
    Gdx.app.log(Engine.LOG, "Setting screen: " + screen.getClass().getSimpleName());
  }

  //endregion
}
