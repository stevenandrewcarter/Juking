package com.juking.engine.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.juking.engine.ai.SteeringBehaviour;

/**
 *
 */
public class Player extends MovingEntity {
  private SpriteBatch batch;
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
    texture = new Texture(Gdx.files.internal("wisp.jpg"));
    rectangle = new Rectangle(800 / 2 - 48 / 2, 20, 48, 48);
    steeringBehaviour = new SteeringBehaviour();
    batch = new SpriteBatch();
  }

  /**
   * @param camera
   */
  @Override
  public void render(Camera camera) {
    move(1);
    batch.setProjectionMatrix(camera.combined);
    // Draw the character
    batch.begin();
    batch.draw(texture, rectangle.x - 24, rectangle.y - 24);
    batch.end();
    shapeRenderer.setProjectionMatrix(camera.combined);
    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    shapeRenderer.setColor(1.0f, 1.0f, 1.0f, 0.1f);
    shapeRenderer.line(position.x, position.y, position.x + heading.x, position.y + heading.y);
    shapeRenderer.line(position.x, position.y, position.x + side.x, position.y + side.y);
    shapeRenderer.end();
    shapeRenderer.begin(ShapeRenderer.ShapeType.Rectangle);
    shapeRenderer.setColor(1.0f, 1.0f, 1.0f, 0.1f);
    shapeRenderer.rect(rectangle.x - 24, rectangle.y - 24, rectangle.width, rectangle.height);
    shapeRenderer.end();
    shapeRenderer.begin(ShapeRenderer.ShapeType.Circle);
    shapeRenderer.setColor(1.0f, 1.0f, 1.0f, 0.1f);
    shapeRenderer.circle(rectangle.x, rectangle.y, boundingRadius);
    shapeRenderer.end();
  }

  /**
   * @param timeElapsed
   */
  @Override
  protected void move(float timeElapsed) {
    // Calculate the combined force from each steering behaviour
    Vector2 steeringForce = steeringBehaviour.calculate();
    setHeading(new Vector2(destination).sub(position).nor());
    position.x = position.x + heading.x;
    position.y = position.y + heading.y;
    rectangle.x = position.x;
    rectangle.y = position.y;
  }
}