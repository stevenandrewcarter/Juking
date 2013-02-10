package com.juking.engine.entities;

import com.badlogic.gdx.math.Vector3;
import com.juking.engine.ai.SteeringBehaviour;

public abstract class MovingEntity extends Entity {
  protected Vector3 velocity;
  protected Vector3 heading;
  protected float mass;
  protected float maxSpeed;
  protected float maxForce;
  protected float maxTurnRate;
  protected SteeringBehaviour steeringBehaviour;

  protected abstract void move(float timeElapsed);
}
