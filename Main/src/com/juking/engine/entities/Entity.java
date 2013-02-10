package com.juking.engine.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public abstract class Entity {

  // Location of the entity in the environment
  protected Vector2 position;
  // The size of the entity
  protected Vector2 scale;
  // Unique identifier for each entity
  protected int id;
  // Indicates the size of the entity
  protected float boundingRadius;

  /**
   * Default constructor for the Entity
   *
   * @param newPosition
   * @param newRadius
   * @param newScale
   */
  public Entity(Vector2 newPosition, float newRadius, Vector2 newScale) {
    // Retrieve the next id for the entity
    id = GetNextId();
    boundingRadius = newRadius;
    scale = newScale;
    position = newPosition;
  }

  /**
   * @param batch
   */
  public abstract void render(SpriteBatch batch);

  /**
   * @return
   */
  private static int GetNextId() {
    int id = 0;
    return id++;
  }
}
