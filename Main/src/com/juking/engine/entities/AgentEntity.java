package com.juking.engine.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.juking.engine.AbstractScreen;
import com.juking.engine.ai.Agent;

/**
 * Entity which represents an agent in the environment
 */
public class AgentEntity extends MovingEntity {

  //region Private Variables
  private Agent agent;
  //endregion

  //region Constructor

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
  public AgentEntity(Vector2 newPosition, float newRadius, Vector2 newScale, AbstractScreen currentWorld, Vector2 newVelocity, Vector2 newHeading, float newMass, float newTurnRate,
                     float newMaxSpeed, Array behaviours) {
    super(newPosition, newRadius, newScale, currentWorld, newVelocity, newHeading, newMass, newTurnRate, newMaxSpeed);
    agent = new Agent(this, behaviours);
    destination = position;
    // texture = new Texture(Gdx.files.internal("images/wisp.jpg"));
    rectangle = new Rectangle(800 / 2 - 32 / 2, 20, 32, 32);
    color = Color.RED;
  }
  //endregion

  //region Protected Methods

  /**
   * Method which moves the entity
   *
   * @param timeElapsed Time Elapsed
   */
  @Override
  protected void move(float timeElapsed) {
    agent.scan();
    setHeading(agent.calculate());
    super.move(timeElapsed);
  }
  //endregion
}
