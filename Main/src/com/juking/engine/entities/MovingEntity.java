package com.juking.engine.entities;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.juking.engine.ai.SteeringBehaviour;

/**
 * Entity which can move in the world. Includes all of the information required to move the entity, such as speed, velocity and headings.
 * Also includes methods which allow the entity to move in the world
 */
public abstract class MovingEntity extends Entity {
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
  protected SteeringBehaviour steeringBehaviour;

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
  public MovingEntity(Vector2 newPosition, float newRadius, Vector2 newScale, Vector2 newVelocity, Vector2 newHeading, float newMass, float newTurnRate, float newMaxSpeed) {
    super(newPosition, newRadius, newScale);
    velocity = newVelocity;
    heading = newHeading;
    mass = newMass;
    maxTurnRate = newTurnRate;
    maxSpeed = newMaxSpeed;
  }

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
  public boolean isSpeedMaxedOut() {
    return maxSpeed * maxSpeed >= velocity.len2();
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
    //side = heading.lerp();
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
    // rotationMatrix.rotate(angle * heading.Sign(toTarget));
    rotationMatrix.translate(heading);
    rotationMatrix.translate(velocity);
    // finally recreate m_vSide
    // side = heading.lerp();
    return false;
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
   * @param x
   */
  public void setDestinationX(float x) {
    destination.x = x;
  }

  /**
   * @param y
   */
  public void setDestinationY(float y) {
    destination.y = y;
  }

  /**
   * @param timeElapsed
   */
  protected abstract void move(float timeElapsed);
}
