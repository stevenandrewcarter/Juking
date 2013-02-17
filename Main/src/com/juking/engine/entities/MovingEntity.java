package com.juking.engine.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.juking.engine.AbstractScreen;
import com.juking.engine.common.Vector2DLibrary;

import java.util.HashMap;
import java.util.Map;

/**
 * Entity which can move in the world. Includes all of the information required to move the entity, such as speed, velocity and headings.
 * Also includes methods which allow the entity to move in the world
 */
public abstract class MovingEntity extends Entity {

  //region Protected Variables
  protected Vector2 velocity;
  // A Normalized vector pointing in the direction the entity is heading.
  protected Vector2 heading;
  // A Vector perpendicular to the heading vector
  protected Vector2 side;
  protected float mass;
  // The maximum speed this entity may travel at.
  protected float maxSpeed;
  protected float maxForce;
  protected float maxTurnRate;
  protected Vector2 destination;
  protected ShapeRenderer shapeRenderer;
  protected SpriteBatch batch;
  protected Rectangle rectangle;
  protected Color color;
  protected Animation animation;
  private float animationState;
  private Map<TextureRegion, Drawable> tiltAnimationDrawables;
  //endregion

  //region Constructor

  /**
   * Defines a entity that can move. The moving entity uses vector based information for moving
   *
   * @param newPosition Position to create the entity
   * @param newRadius   Collision radius for the entity
   * @param newScale    Scale of the entity (Size)
   * @param newVelocity Velocity that the entity travels at
   * @param newHeading  Heading that the entity is pointing
   * @param newMass     Mass (Weight) of the entity
   * @param newTurnRate Rate at which the entity can turn
   * @param newMaxSpeed Maximum speed that the entity can travel at
   */
  public MovingEntity(Vector2 newPosition, float newRadius, Vector2 newScale, AbstractScreen currentWorld, Vector2 newVelocity, Vector2 newHeading, float newMass,
                      float newTurnRate, float newMaxSpeed, Array<TextureAtlas.AtlasRegion> animationFrames) {
    super(newPosition, newRadius, newScale, currentWorld, animationFrames);
    setVelocity(newVelocity);
    setHeading(newHeading);
    mass = newMass;
    setMaxTurnRate(newTurnRate);
    setMaxSpeed(newMaxSpeed);
    shapeRenderer = new ShapeRenderer();
    batch = new SpriteBatch();
    // Set the color to white, just in case it wasn't already set
    color = Color.WHITE;
    this.animation = new Animation(0.15f, animationFrames);
    this.tiltAnimationDrawables = new HashMap<TextureRegion, Drawable>();
    for (TextureAtlas.AtlasRegion region : animationFrames) {
      tiltAnimationDrawables.put(region, new TextureRegionDrawable(region));
    }
    animationState = 0;
  }
  //endregion

  //region Properties

  /**
   * @return
   */
  public Vector2 getVelocity() {
    return velocity;
  }

  /**
   * @param newVelocity
   */
  public void setVelocity(Vector2 newVelocity) {
    velocity = newVelocity;
  }

  /**
   * @return
   */
  public float getMass() {
    return mass;
  }

  /**
   * @return
   */
  public Vector2 getSide() {
    return side;
  }

  /**
   * @return
   */
  public float getMaxSpeed() {
    return maxSpeed;
  }

  /**
   * @param newMaxSpeed
   */
  public void setMaxSpeed(float newMaxSpeed) {
    maxSpeed = newMaxSpeed;
  }

  /**
   * @return
   */
  public float getMaxForce() {
    return maxForce;
  }

  /**
   * @param newMaxForce
   */
  public void setMaxForce(float newMaxForce) {
    maxForce = newMaxForce;
  }

  /**
   * @return
   */
  public float speed() {
    return velocity.len();
  }

  /**
   * @return
   */
  public float speedSquared() {
    return velocity.len2();
  }

  /**
   * @return
   */
  public Vector2 getHeading() {
    return heading;
  }

  /**
   * First checks that the given heading is not a vector of zero length. If the new heading is valid this function sets the entity's heading and side vectors accordingly
   *
   * @param newHeading
   */
  public void setHeading(Vector2 newHeading) {
    assert ((heading.len2() - 1.0) < 0.00001);
    heading = newHeading;
    // The side vector must always be perpendicular to the heading
    side = Vector2DLibrary.Perpendicular(heading);
  }

  /**
   * @return
   */
  public float getMaxTurnRate() {
    return maxTurnRate;
  }

  /**
   * @param newMaxTurnRate
   */
  public void setMaxTurnRate(float newMaxTurnRate) {
    maxTurnRate = newMaxTurnRate;
  }

  /**
   * @param newDestination
   */
  public void setDestination(Vector2 newDestination) {
    destination = newDestination;
  }
  //endregion

  //region Public Methods

  /**
   * Renders the given entity
   *
   * @param camera Camera which is used to render the entity
   */
  @Override
  public void render(Camera camera, float delta) {
    move(delta);
    batch.setProjectionMatrix(camera.combined);
    // Draw the character
    renderEntity(delta);
    shapeRenderer.setProjectionMatrix(camera.combined);
    renderPositionVectors();
    renderTextureRectangle();
    renderRadius();
  }

  /**
   * Given a target position, this method rotates the entity's heading and side vectors by an amount not greater than maxTurnRate until it directly faces the target.
   *
   * @param target
   * @return Returns true when the heading is facing in the desired direction
   */
  public boolean rotateHeadingToFacePosition(Vector2 target) {
    Vector2 toTarget = target.sub(position).nor();
    // First determine the angle between the heading vector and the target
    float angle = (float) Math.acos(heading.dot(toTarget));
    // Return true if the player is facing the target
    if (angle < 0.00001) {
      return true;
    }
    // Clamp the amount to turn to the max turn rate
    if (angle > maxTurnRate) {
      angle = maxTurnRate;
    }
    // The next few lines use a rotation matrix to rotate the player's heading vector accordingly
    Matrix3 rotationMatrix = new Matrix3();
    //notice how the direction of rotation has to be determined when creating the rotation matrix
    rotationMatrix.rotate(angle * Vector2DLibrary.Sign(heading, toTarget));
    rotationMatrix.translate(heading);
    rotationMatrix.translate(velocity);
    // finally recreate the side vector
    side = Vector2DLibrary.Perpendicular(heading);
    return false;
  }

  /**
   * @return
   */
  public boolean isSpeedMaxedOut() {
    return maxSpeed * maxSpeed >= velocity.len2();
  }
  //endregion

  //region Protected Methods

  /**
   * Method which moves the entity
   *
   * @param timeElapsed Time Elapsed
   */
  protected void move(float timeElapsed) {
    if (position.x < 0) position.x = 0;
    else if (position.x > 800 - rectangle.width) position.x = 800 - rectangle.width;
    if (position.y < 0) position.y = 0;
    else if (position.y > 480 - rectangle.height) position.y = 480 - rectangle.height;
    setPosition(new Vector2(position).add(heading));
    rectangle.x = position.x;
    rectangle.y = position.y;
  }

  /**
   * Renders the entity
   */
  protected void renderEntity(float delta) {
    animationState += delta;
    if (animationState > 3) {
      animationState = 0;
    }
    // the animation's frame to be shown
    TextureRegion frame = animation.getKeyFrame(animationState, false);
    batch.begin();
    tiltAnimationDrawables.get(frame).draw(batch, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    batch.end();
  }

  /**
   * Renders the radius. This should only be used for debugging
   */

  protected void renderRadius() {
    shapeRenderer.begin(ShapeRenderer.ShapeType.Circle);
    shapeRenderer.setColor(color);
    shapeRenderer.circle(rectangle.x + (rectangle.width / 2), rectangle.y + (rectangle.height / 2), boundingRadius);
    shapeRenderer.end();
  }

  /**
   * Renders the texture rectangle. This is only for debugging
   */
  protected void renderTextureRectangle() {
    shapeRenderer.begin(ShapeRenderer.ShapeType.Rectangle);
    shapeRenderer.setColor(color);
    shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    shapeRenderer.end();
  }

  /**
   * Renders the position vectors. This is only for debugging
   */
  protected void renderPositionVectors() {
    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    shapeRenderer.setColor(color);
    shapeRenderer.line(position.x, position.y, position.x + heading.x, position.y + heading.y);
    shapeRenderer.line(position.x, position.y, position.x + side.x, position.y + side.y);
    shapeRenderer.end();
  }
  //endregion
}
