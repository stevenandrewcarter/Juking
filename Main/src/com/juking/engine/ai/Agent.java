package com.juking.engine.ai;

import com.badlogic.gdx.math.Vector2;
import com.juking.engine.ai.behaviours.Steering;
import com.juking.engine.entities.MovingEntity;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Agent {

  protected MovingEntity entity;
  protected List<Steering> steering;
  protected List<MovingEntity> entities;
  protected Vector2 steeringForce;
  protected MovingEntity target;
  protected float boundingBoxLength;
  // Feelers are used to avoid obstacle
  protected List<Vector2> feelers;
  protected float feelerLength;

  /**
   * @param newEntity
   */
  public Agent(MovingEntity newEntity) {
    entity = newEntity;
    steering = new LinkedList<Steering>();
    steeringForce = new Vector2();
    entities = new LinkedList<MovingEntity>();
    feelers = new LinkedList<Vector2>();
    boundingBoxLength = 0;
    feelerLength = 0;
  }
}
