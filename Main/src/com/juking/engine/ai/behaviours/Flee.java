package com.juking.engine.ai.behaviours;

import com.badlogic.gdx.math.Vector2;
import com.juking.engine.entities.MovingEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 10/02/13
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class Flee extends Steering {

  public Flee(MovingEntity newEntity) {
    super(newEntity);
  }

  @Override
  public Vector2 calculate() {
    return new Vector2();
  }
}
