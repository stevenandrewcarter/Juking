package com.juking.engine.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.juking.engine.AbstractScreen;

/**
 * Represents a Player Entity in the world
 */
public class PlayerEntity extends MovingEntity {

  //region Constructor

  /**
   * Creates a new entity which represents a player in the world
   *
   * @param newPosition Position to create the entity
   * @param newRadius   Collision radius for the entity
   * @param newScale    Scale of the entity (Size)
   * @param newVelocity Velocity that the entity travels at
   * @param newHeading  Heading that the entity is pointing
   * @param newMass     Mass (Weight) of the entity
   * @param newTurnRate Rate at which the entity can turn
   * @param newMaxSpeed Maximum speed that the entity can travel at
   */
  public PlayerEntity(Vector2 newPosition, float newRadius, Vector2 newScale, AbstractScreen currentWorld, Vector2 newVelocity, Vector2 newHeading, float newMass, float newTurnRate,
                      float newMaxSpeed, Array<TextureAtlas.AtlasRegion> regions) {
    super(newPosition, newRadius, newScale, currentWorld, newVelocity, newHeading, newMass, newTurnRate, newMaxSpeed, regions);
    destination = position;
    // texture = new Texture(Gdx.files.internal("images/wisp.jpg"));
    rectangle = new Rectangle(800 / 2 - 32 / 2, 20, 32, 32);
    color = Color.BLUE;
  }
  //endregion

  //region Public Methods

  /**
   * Renders the given entity
   *
   * @param camera Camera which is used to render the entity
   */
  @Override
  public void render(Camera camera) {
    super.render(camera);
    if (Gdx.input.isTouched()) {
      Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);
      setDestination(new Vector2(touchPos.x - 24 / 2, touchPos.y - 24 / 2));
    }
    setHeading(new Vector2(destination).sub(position).nor());
  }
  //endregion
}