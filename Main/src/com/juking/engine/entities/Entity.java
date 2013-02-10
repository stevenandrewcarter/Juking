package com.juking.engine.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.juking.engine.Engine;

/**
 *
 */
public abstract class Entity {

  //region Protected Variables
  // Location of the entity in the environment
  protected Vector2 position;
  // The size of the entity
  protected Vector2 scale;
  // Unique identifier for each entity
  protected int id;
  // Indicates the size of the entity
  protected float boundingRadius;
  // World which the entity exists in
  protected Engine world;
  //endregion

  //region Constructor

  /**
   * Default constructor for the Entity
   *
   * @param newPosition  Position of the entity in the world
   * @param newRadius    Radius of the entity
   * @param newScale     Size of the entity
   * @param currentWorld World the entity exists in
   */
  public Entity(Vector2 newPosition, float newRadius, Vector2 newScale, Engine currentWorld) {
    // Retrieve the next id for the entity
    id = GetNextId();
    boundingRadius = newRadius;
    scale = newScale;
    position = newPosition;
    world = currentWorld;
  }
  //endregion

  //region Properties

  /**
   * Returns the position of the entity in the world
   *
   * @return A vector which represents the position in the world
   */
  public Vector2 getPosition() {
    return position;
  }

  /**
   * Sets the position of the entity in the world
   *
   * @param newPosition New position of the entity
   */
  public void setPosition(Vector2 newPosition) {
    position = newPosition;
  }

  /**
   * The world the entity exists in
   *
   * @return The world
   */
  public Engine getWorld() {
    return world;
  }
  //endregion

  //region Abstract Methods

  /**
   * Renders the given entity
   *
   * @param camera Camera which is used to render the entity
   */
  public abstract void render(Camera camera);
  //endregion

  //region Private Static Methods

  /**
   * Gets the next available identifier for the entity
   *
   * @return Next Integer identifier for an entity
   */
  private static int GetNextId() {
    int id = 0;
    return id++;
  }
  //endregion
}
