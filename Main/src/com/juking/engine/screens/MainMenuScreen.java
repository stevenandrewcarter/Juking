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
 * Time: 14:29
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuScreen extends AbstractScreen {
  public MainMenuScreen(Engine currentEngine) {
    super(currentEngine);
  }


  @Override
  public void show() {
    super.show();
    // retrieve the default table actor
    Table table = super.getTable();
    table.add("Juking!").spaceBottom(50);
    table.row();
    // register the button "start game"
    TextButton startGameButton = new TextButton("Start game", getSkin());
    startGameButton.addListener(new EventListener() {
      @Override
      public boolean handle(Event event) {
        if (event.toString().equals("touchDown")) {
          engine.setScreen(new StartGameScreen(engine));
          return true;
        }
        return false;
      }
    });
    table.add(startGameButton).size(300, 60).uniform().spaceBottom(10);
    table.row();

    // register the button "options"
    TextButton optionsButton = new TextButton("Options", getSkin());
    table.add(optionsButton).uniform().fill().spaceBottom(10);
    table.row();

//    // register the button "high scores"
//    TextButton highScoresButton = new TextButton("High Scores", getSkin());
//    table.add(highScoresButton).uniform().fill();
  }
}
