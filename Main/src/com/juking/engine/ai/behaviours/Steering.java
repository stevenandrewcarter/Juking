package com.juking.engine.ai.behaviours;

import com.badlogic.gdx.math.Vector2;
import com.juking.engine.entities.MovingEntity;

/**
 *
 */
public abstract class Steering {

  protected MovingEntity entity;

  public Steering(MovingEntity newEntity) {
    entity = newEntity;
  }

  /**
   * @return
   */
  public abstract Vector2 calculate();

}
