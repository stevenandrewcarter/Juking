package com.juking.engine.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class Entity {

  // Location of the entity in the environment
  protected Vector3 position;
  // Unique identifier for each entity
  protected int id;
  // Indicates the size of the entity
  protected float boundingRadius;

  public abstract void render(SpriteBatch batch);

  public Entity(int entityId, float newRadius) {
    id = entityId;
    boundingRadius = newRadius;
  }
}
