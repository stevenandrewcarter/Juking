package com.juking.engine.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.juking.engine.ai.behaviours.Steering;

/**
 *
 */
public class PlayerEntity extends MovingEntity {

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
  public PlayerEntity(Vector2 newPosition, float newRadius, Vector2 newScale, Vector2 newVelocity, Vector2 newHeading, float newMass, float newTurnRate, float newMaxSpeed) {
    super(newPosition, newRadius, newScale, newVelocity, newHeading, newMass, newTurnRate, newMaxSpeed);
    destination = position;
    texture = new Texture(Gdx.files.internal("wisp.jpg"));
    rectangle = new Rectangle(800 / 2 - 48 / 2, 20, 48, 48);
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
    renderEntity();
    shapeRenderer.setProjectionMatrix(camera.combined);
    renderPositionVectors();
    renderTextureRectangle();
    renderRadius();
  }

  private void renderEntity() {
    batch.begin();
    batch.draw(texture, rectangle.x - 24, rectangle.y - 24);
    batch.end();
  }

  private void renderRadius() {
    shapeRenderer.begin(ShapeRenderer.ShapeType.Circle);
    shapeRenderer.setColor(1.0f, 1.0f, 1.0f, 0.1f);
    shapeRenderer.circle(rectangle.x, rectangle.y, boundingRadius);
    shapeRenderer.end();
  }

  private void renderTextureRectangle() {
    shapeRenderer.begin(ShapeRenderer.ShapeType.Rectangle);
    shapeRenderer.setColor(1.0f, 1.0f, 1.0f, 0.1f);
    shapeRenderer.rect(rectangle.x - 24, rectangle.y - 24, rectangle.width, rectangle.height);
    shapeRenderer.end();
  }

  private void renderPositionVectors() {
    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    shapeRenderer.setColor(1.0f, 1.0f, 1.0f, 0.1f);
    shapeRenderer.line(position.x, position.y, position.x + heading.x, position.y + heading.y);
    shapeRenderer.line(position.x, position.y, position.x + side.x, position.y + side.y);
    shapeRenderer.end();
  }

  /**
   * @param timeElapsed
   */
  @Override
  protected void move(float timeElapsed) {
    setHeading(new Vector2(destination).sub(position).nor());
    position.x = position.x + heading.x;
    position.y = position.y + heading.y;
    rectangle.x = position.x;
    rectangle.y = position.y;
  }
}