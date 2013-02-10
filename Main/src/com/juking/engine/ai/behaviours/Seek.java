package com.juking.engine.ai.behaviours;

import com.badlogic.gdx.math.Vector2;
import com.juking.engine.ai.Agent;

/**
 * Given a target, this behavior returns a steering force which will direct the agent towards the target
 */
public class Seek extends Steering {

  /**
   * Constructs a seeking behaviour
   *
   * @param newAgent Agent which utilises this behaviour
   */
  public Seek(Agent newAgent) {
    super(newAgent);
  }

  /**
   * Calculates the desired vector for implementing the behaviour
   *
   * @return A vector to the desired location that the agent wants to move towards
   */
  @Override
  public Vector2 calculate() {
    // Normal(TargetPos - Agent.Position) * Agent.MaxSpeed
    Vector2 targetPosition = agent.getTarget().getPosition().tmp();
    Vector2 agentPosition = agent.getEntity().getPosition().tmp();
    float agentMaxSpeed = agent.getEntity().getMaxSpeed();
    Vector2 desiredVelocity = targetPosition.sub(agentPosition).nor().mul(agentMaxSpeed);
    return desiredVelocity.sub(agent.getEntity().getVelocity());
  }
}
