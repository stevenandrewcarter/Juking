package com.juking.engine.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.juking.engine.ai.SteeringBehaviour;

/**
 *
 */
public class Player extends MovingEntity {
  private Texture texture;
  private Rectangle rectangle;

  /**
   * @param newPosition
   * @param newRadius
   * @param newScale
   * @param newVelocity
   * @param newHeading
   * @param newMass
   * @param newTurnRate
   * @param newMaxSpeed
   */
  public Player(Vector2 newPosition, float newRadius, Vector2 newScale, Vector2 newVelocity, Vector2 newHeading, float newMass, float newTurnRate, float newMaxSpeed) {
    super(newPosition, newRadius, newScale, newVelocity, newHeading, newMass, newTurnRate, newMaxSpeed);
    destination = new Vector2(800 / 2 - 48 / 2, 20);
    texture = new Texture(Gdx.files.internal("character.gif"));
    rectangle = new Rectangle(800 / 2 - 48 / 2, 20, 48, 48);
    steeringBehaviour = new SteeringBehaviour();
  }

  /**
   * @param batch
   */
  @Override
  public void render(SpriteBatch batch) {
    move(1);
    batch.begin();
    batch.draw(texture, rectangle.x, rectangle.y);
    batch.end();
  }

  /**
   * @param timeElapsed
   */
  @Override
  protected void move(float timeElapsed) {
    // Calculate the combined force from each steering behaviour
    Vector2 steeringForce = steeringBehaviour.calculate();
    Vector2 move = new Vector2(destination).sub(position).nor();
    position.x = position.x + move.x;
    position.y = position.y + move.y;
    rectangle.x = position.x;
    rectangle.y = position.y;
  }
}