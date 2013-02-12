package com.juking.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
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
  private TileMapRenderer tileMapRenderer;
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
    // Load the tmx file into map
    TiledMap map = TiledLoader.createMap(Gdx.files.internal("engine/data/testmap.tmx"));
    // Load the tiles into atlas
    TileAtlas atlas = new TileAtlas(map, Gdx.files.internal("engine/data/"));
    // Create the renderer
    tileMapRenderer = new TileMapRenderer(map, atlas, 32, 32, 32, 32);
    Gdx.app.log(this.getClass().getName(), "Created the Juking Environment");
  }

  /**
   * Renders the engine. This is an override from the libgdx
   */
  @Override
  public void render() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    camera.update();
    tileMapRenderer.render(camera);
    for (Iterator<MovingEntity> entity = movingEntities.iterator(); entity.hasNext(); ) {
      entity.next().render(camera);
    }
  }
  //endregion

  //region Private Methods

  /**
   * Loads the default engine configuration
   */
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

  /**
   * Load the agents from the configuration
   *
   * @param entities Entities json information
   */
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
              Float.parseFloat(agentEntity.get("max_speed").toString()),
              (Array) agentEntity.get("behaviours")));
    }
  }

  /**
   * Load the player information
   *
   * @param entities Entities json information
   */
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
  //endregion
}
