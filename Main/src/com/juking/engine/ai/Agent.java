package com.juking.engine.ai;

import com.badlogic.gdx.math.Vector2;
import com.juking.engine.Engine;
import com.juking.engine.ai.behaviours.Flee;
import com.juking.engine.ai.behaviours.Seek;
import com.juking.engine.ai.behaviours.Steering;
import com.juking.engine.entities.MovingEntity;
import com.juking.engine.entities.PlayerEntity;

import java.util.*;

/**
 * Agent information is available in this class. The ability to move and make choices are calculated here.
 */
public class Agent {

  //region Protected Variables
  protected MovingEntity entity;
  protected Map<String, Steering> steering;
  protected List<MovingEntity> entities;
  protected Vector2 steeringForce;
  protected MovingEntity target;
  protected float boundingBoxLength;
  // Feelers are used to avoid obstacle
  protected List<Vector2> feelers;
  protected float feelerLength;
  //endregion

  //region Constructor

  /**
   * Creates a new Agent for the entity
   *
   * @param newEntity Entity which represents the agent in the world
   */
  public Agent(MovingEntity newEntity) {
    entity = newEntity;
    steering = new HashMap<String, Steering>();
    steeringForce = new Vector2();
    entities = new LinkedList<MovingEntity>();
    feelers = new LinkedList<Vector2>();
    boundingBoxLength = 0;
    feelerLength = 0;
    addSteeringBehaviour("Seek", new Seek(this));
  }
  //endregion

  //region Properties

  /**
   * Gets the target that the agent has selected.
   *
   * @return A entity that the Agent is currently trying to get to or away from
   */
  public MovingEntity getTarget() {
    return target;
  }

  /**
   * Sets the agents target
   *
   * @param newTarget MovingEntity which represents the agents target
   */
  public void setTarget(MovingEntity newTarget) {
    target = newTarget;
  }

  /**
   * Retrieves the entity which represents the agent in the world
   *
   * @return A MovingEntity which represents the agent
   */
  public MovingEntity getEntity() {
    return entity;
  }
  //endregion

  //region Public Methods

  /**
   * Allows the agent to determine what it can see in the world
   */
  public void scan() {
    Engine world = entity.getWorld();
    for (Iterator<MovingEntity> entity = world.getMovingEntities().iterator(); entity.hasNext(); ) {
      MovingEntity currentEntity = entity.next();
      if (currentEntity instanceof PlayerEntity) {
        setTarget(currentEntity);
      }
    }
  }

  /**
   * Adds a steering behaviour to the agent
   *
   * @param behaviourName Name of the behaviour to add. This is used to look up the behaviour afterwards
   * @param behaviour     Behaviour to add
   */
  public void addSteeringBehaviour(String behaviourName, Steering behaviour) {
    steering.put(behaviourName, behaviour);
  }

  /**
   * @param behaviourName
   */
  public void removeSteeringBehaviour(String behaviourName) {
    steering.remove(behaviourName);
  }

  /**
   * Creates the antenna utilized by the wall avoidance behavior
   */
  public void createFeelers() {

  }

  /**
   * Calculates the desired movement for the agent
   *
   * @return A vector indicating where the agent wants to move towards
   */
  public Vector2 calculate() {
    Vector2 result = new Vector2();
    for (Iterator<Steering> behaviour = steering.values().iterator(); behaviour.hasNext(); ) {
      result.add(behaviour.next().calculate());
    }
    return result;
  }
  //endregion
}
