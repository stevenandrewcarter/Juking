package com.juking.engine.ai.behaviours;

import com.badlogic.gdx.math.Vector2;
import com.juking.engine.ai.Agent;

/**
 *
 */
public abstract class Steering {

  protected Agent agent;

  /**
   * Default constructor for steering behaviours
   *
   * @param newAgent Agent which utilises this behaviour
   */
  public Steering(Agent newAgent) {
    agent = newAgent;
  }

  /**
   * Calculates the desired vector for implementing the behaviour
   *
   * @return A vector to the desired location that the agent wants to move towards
   */
  public abstract Vector2 calculate();

}
