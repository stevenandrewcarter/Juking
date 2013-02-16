package com.juking.engine.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.juking.engine.AbstractScreen;
import com.juking.engine.Engine;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 *
 */
public class SplashScreen extends AbstractScreen {
  private Image splashImage;

  public SplashScreen(Engine engine) {
    super(engine);
  }

  @Override
  public void show() {
    super.show();
    // retrieve the splash image's region from the atlas
    TextureAtlas.AtlasRegion splashRegion = getAtlas().findRegion("splash");
    Drawable splashDrawable = new TextureRegionDrawable(splashRegion);
    // here we create the splash image actor; its size is set when the
    // resize() method gets called
    splashImage = new Image(splashDrawable, Scaling.stretch);
    splashImage.setFillParent(true);
    // this is needed for the fade-in effect to work correctly; we're just
    // making the image completely transparent
    splashImage.getColor().a = 0f;
    // configure the fade-in/out effect on the splash image
    splashImage.addAction(sequence(fadeIn(0.75f), delay(1.75f), fadeOut(0.75f),
            new Action() {
              @Override
              public boolean act(
                      float delta) {
                // the last action will move to the next screen
                engine.setScreen(new MainMenuScreen(engine));
                return true;
              }
            }));
    // and finally we add the actor to the stage
    stage.addActor(splashImage);
  }
}
