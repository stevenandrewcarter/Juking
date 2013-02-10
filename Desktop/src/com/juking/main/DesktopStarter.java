package com.juking.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.juking.engine.Engine;

public class DesktopStarter {
  // Desktop entry point
  public static void main(String[] args) {
    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = "Juking";
    cfg.useGL20 = true;
    cfg.width = 800;
    cfg.height = 480;
    new LwjglApplication(new Engine(), cfg);
  }
}