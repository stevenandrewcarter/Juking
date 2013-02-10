package com.juking.engine.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.juking.engine.ai.Agent;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 10/02/13
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 */
public class AgentEntity extends MovingEntity {

  private Agent agent;

  /**
   * Defines a entity that can move. The moving entity uses vector based information for moving
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
  public AgentEntity(Vector2 newPosition, float newRadius, Vector2 newScale, Vector2 newVelocity, Vector2 newHeading, float newMass, float newTurnRate, float newMaxSpeed) {
    super(newPosition, newRadius, newScale, newVelocity, newHeading, newMass, newTurnRate, newMaxSpeed);
    agent = new Agent();
  }

  @Override
  protected void move(float timeElapsed) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void render(Camera camera) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
