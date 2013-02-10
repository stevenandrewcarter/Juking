package com.juking.engine.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public class Opponent extends MovingEntity {

  /**
   * @param newPosition
   * @param newRadius
   * @param newScale
   * @param newVelocity
   * @param newHeading
   * @param newMass
   * @param newTurnRate
   * @param newMaxSpeed
   */
  public Opponent(Vector2 newPosition, float newRadius, Vector2 newScale, Vector2 newVelocity, Vector2 newHeading, float newMass, float newTurnRate, float newMaxSpeed) {
    super(newPosition, newRadius, newScale, newVelocity, newHeading, newMass, newTurnRate, newMaxSpeed);
  }

  /**
   * @param camera
   */
  @Override
  public void render(Camera camera) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  /**
   * @param timeElapsed
   */
  @Override
  protected void move(float timeElapsed) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
