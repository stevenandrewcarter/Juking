package com.juking.engine.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.juking.engine.ai.SteeringBehaviour;

public class Player extends MovingEntity {
  private Texture texture;
  private Rectangle rectangle;

  public Player() {
    source = new Vector3(800 / 2 - 48 / 2, 20, 0);
    destination = new Vector3(800 / 2 - 48 / 2, 20, 0);
    texture = new Texture(Gdx.files.internal("character.gif"));
    rectangle = new Rectangle(800 / 2 - 48 / 2, 20, 48, 48);
    steeringBehaviour = new SteeringBehaviour();
  }

  @Override
  public void render(SpriteBatch batch) {
    move(1);
    batch.begin();
    batch.draw(texture, rectangle.x, rectangle.y);
    batch.end();
  }

  @Override
  protected void move(float timeElapsed) {
    // Calculate the combined force from each steering behaviour
    Vector3 steeringForce = steeringBehaviour.calculate();
    Vector3 move = new Vector3(destination).sub(source).nor();
    source.x = source.x + move.x;
    source.y = source.y + move.y;
    rectangle.x = source.x;
    rectangle.y = source.y;
  }
}