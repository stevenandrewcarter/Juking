package com.juking.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.OrderedMap;
import com.juking.engine.entities.AgentEntity;
import com.juking.engine.entities.MovingEntity;
import com.juking.engine.entities.PlayerEntity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Engine extends Game {

  //region Private Variables
  private OrthographicCamera camera;
  private List<MovingEntity> movingEntities;
  //endregion

  //region Properties

  /**
   * Get the list of moving entities from the engine
   *
   * @return A List of moving entities
   */
  public List<MovingEntity> getMovingEntities() {
    return movingEntities;
  }
  //endregion

  //region Public Methods

  /**
   * Creates the engine. This is an override from the libgdx
   */
  @Override
  public void create() {
    movingEntities = new LinkedList<MovingEntity>();
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
    loadConfiguration();
  }

  /**
   * Renders the engine. This is an override from the libgdx
   */
  @Override
  public void render() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    camera.update();
    for (Iterator<MovingEntity> entity = movingEntities.iterator(); entity.hasNext(); ) {
      entity.next().render(camera);
    }
  }
  //endregion

  private void loadConfiguration() {
    // Load the json configuration
    Object engine_config = new JsonReader().parse(Gdx.files.internal("engine/engine.json"));
    // Load the entities
    Object entities = ((OrderedMap) engine_config).get("entities");
    // Load the Player
    loadPlayer((OrderedMap) entities);
    // Load the Agents
    loadAgents((OrderedMap) entities);
  }

  private void loadAgents(OrderedMap entities) {
    Array agents = (Array) entities.get("agents");
    for (Iterator<OrderedMap> agent = agents.iterator(); agent.hasNext(); ) {
      OrderedMap agentEntity = agent.next();
      movingEntities.add(new AgentEntity(new Vector2(48, 48),
              Float.parseFloat(agentEntity.get("radius").toString()),
              new Vector2(1.0f, 1.0f),
              this,
              new Vector2(),
              new Vector2(1.0f, 0.0f),
              Float.parseFloat(agentEntity.get("mass").toString()),
              Float.parseFloat(agentEntity.get("turn_rate").toString()),
              Float.parseFloat(agentEntity.get("max_speed").toString())));
    }
  }

  private void loadPlayer(OrderedMap entities) {
    OrderedMap player = (OrderedMap) entities.get("player");
    movingEntities.add(new PlayerEntity(new Vector2(800 / 2, 300),
            Float.parseFloat(player.get("radius").toString()),
            new Vector2(1.0f, 1.0f),
            this,
            new Vector2(),
            new Vector2(1.0f, 0.0f),
            Float.parseFloat(player.get("mass").toString()),
            Float.parseFloat(player.get("turn_rate").toString()),
            Float.parseFloat(player.get("max_speed").toString())));
  }
}
