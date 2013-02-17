package com.juking.engine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.OrderedMap;
import com.juking.engine.AbstractScreen;
import com.juking.engine.Engine;
import com.juking.engine.entities.AgentEntity;
import com.juking.engine.entities.Entity;
import com.juking.engine.entities.PlayerEntity;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 17/02/13
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen extends AbstractScreen {

  private OrthographicCamera camera;
  private TileMapRenderer tileMapRenderer;


  public GameScreen(Engine currentEngine) {
    super(currentEngine);
    movingEntities = new LinkedList<Entity>();
    loadConfiguration();
  }

  @Override
  public void show() {
    super.show();
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
    // Load the tmx file into map
    TiledMap map = TiledLoader.createMap(Gdx.files.internal("engine/data/testmap.tmx"));
    // Load the tiles into atlas
    TileAtlas atlas = new TileAtlas(map, Gdx.files.internal("engine/data/"));
    // Create the renderer
    tileMapRenderer = new TileMapRenderer(map, atlas, 32, 32, 32, 32);
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    camera.update();
    tileMapRenderer.render(camera);
    for (Iterator<Entity> entity = movingEntities.iterator(); entity.hasNext(); ) {
      entity.next().render(camera, delta);
    }
  }

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
    Array<TextureAtlas.AtlasRegion> regions = getAtlas().findRegions("player");
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
              (Array) agentEntity.get("behaviours"), regions));
    }
  }

  /**
   * Load the player information
   *
   * @param entities Entities json information
   */
  private void loadPlayer(OrderedMap entities) {
    // load all the regions of our ship in the image atlas
    Array<TextureAtlas.AtlasRegion> regions = getAtlas().findRegions("player");
    OrderedMap player = (OrderedMap) entities.get("player");
    Entity playerEntity = new PlayerEntity(new Vector2(800 / 2, 300),
            Float.parseFloat(player.get("radius").toString()),
            new Vector2(1.0f, 1.0f),
            this,
            new Vector2(),
            new Vector2(1.0f, 0.0f),
            Float.parseFloat(player.get("mass").toString()),
            Float.parseFloat(player.get("turn_rate").toString()),
            Float.parseFloat(player.get("max_speed").toString()),
            regions);
    movingEntities.add(playerEntity);
    stage.addActor(playerEntity);
  }
  //endregion
}
