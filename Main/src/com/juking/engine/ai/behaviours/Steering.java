package com.juking.engine.ai.behaviours;

import com.badlogic.gdx.math.Vector2;
import com.juking.engine.ai.Agent;

/**
 *
 */
public abstract class Steering {

  protected Agent agent;

  //region Constructor

  /**
   * Default constructor for steering behaviours
   *
   * @param newAgent Agent which utilises this behaviour
   */
  protected Steering(Agent newAgent) {
    agent = newAgent;
  }
  //endregion

  //region Factory Constructor

  /**
   * Creates a new behaviour for the given agent
   *
   * @param newAgent  Agent which the behaviour belongs to
   * @param behavoiur Name of the behaviour to create
   * @return A new steering behaviour
   */
  public static Steering create(Agent newAgent, String behavoiur) {
    Steering newBehaviour = null;
    if (behavoiur.equals("Seek")) {
      newBehaviour = new Seek(newAgent);
    } else if (behavoiur.equals("Flee")) {
      newBehaviour = new Flee(newAgent);
    }
    return newBehaviour;
  }
  //endregion

  //region Abstract Methods

  /**
   * Calculates the desired vector for implementing the behaviour
   *
   * @return A vector to the desired location that the agent wants to move towards
   */
  public abstract Vector2 calculate();
  //endregion
}
