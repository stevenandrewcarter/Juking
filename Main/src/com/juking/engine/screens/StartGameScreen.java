package com.juking.engine.screens;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.juking.engine.AbstractScreen;
import com.juking.engine.Engine;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 16/02/13
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class StartGameScreen extends AbstractScreen {

  public StartGameScreen(Engine currentEngine) {
    super(currentEngine);
  }

  @Override
  public void show() {
    super.show();
    // retrieve the default table actor
    Table table = super.getTable();
    table.add("Loadout Screen / Level Selection!").spaceBottom(50);
    table.row();
    // register the button "start game"
    TextButton startGameButton = new TextButton("Begin", getSkin());
    startGameButton.addListener(new EventListener() {
      @Override
      public boolean handle(Event event) {
        if (event.toString().equals("touchDown")) {
          engine.setScreen(new GameScreen(engine));
          return true;
        }
        return false;
      }
    });
    table.add(startGameButton).size(300, 60).uniform().spaceBottom(10);
    table.row();
  }
}
