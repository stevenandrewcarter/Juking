# Juking

## Map Generation

Get the map editor from http://www.mapeditor.org/

Run the following command to regenerate the maps for the Juking application
java -classpath libs/gdx.jar;libs/gdx-tools.jar;libs/gdx-tiled-preprocessor.jar com.badlogic.gdx.tiledmappacker.TiledMapPacker ..\maps ..\Android\assets\engine\data --strip-unused