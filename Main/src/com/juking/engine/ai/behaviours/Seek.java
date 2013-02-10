package com.juking.engine.ai.behaviours;

import com.badlogic.gdx.math.Vector2;
import com.juking.engine.entities.MovingEntity;

/**
 *
 */
public class Seek extends Steering {

  public Seek(MovingEntity newEntity) {
    super(newEntity);
  }

  @Override
  public Vector2 calculate() {
    return new Vector2();
  }
}
