package com.juking.engine.ai.behaviours;

import com.badlogic.gdx.math.Vector2;
import com.juking.engine.ai.Agent;

/**
 * Given a target, this behavior returns a steering force which will direct the agent away from the target
 */
public class Flee extends Steering {

  /**
   * Constructs a fleeing behaviour
   *
   * @param newAgent Agent which utilises this behaviour
   */
  public Flee(Agent newAgent) {
    super(newAgent);
  }

  /**
   * Calculates the desired vector for implementing the behaviour
   *
   * @return A vector to the desired location that the agent wants to move towards
   */
  @Override
  public Vector2 calculate() {
    // Normal(Agent.Position - TargetPos) * Agent.MaxSpeed
    Vector2 targetPosition = agent.getTarget().getPosition();
    Vector2 agentPosition = agent.getEntity().getPosition();
    float agentMaxSpeed = agent.getEntity().getMaxSpeed();
    Vector2 desiredVelocity = agentPosition.tmp().sub(targetPosition).nor().mul(agentMaxSpeed);
    return desiredVelocity.sub(agent.getEntity().getVelocity());
  }
}
