package com.juking.engine.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class Entity {

  // Protected Variables
  protected Vector3 source;
  protected Vector3 destination;

  public abstract void render(SpriteBatch batch);

  protected abstract void move();

  public void setX(float x) {
    destination.x = x;
  }

  public void setY(float y) {
    destination.y = y;
  }
}
