package com.juking.engine.common;

import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public class Vector2DLibrary {

  /**
   * Returns positive if vector2 is clockwise of vector1, minus if anticlockwise (Y axis pointing down, X axis to right)
   *
   * @param vector1
   * @param vector2
   * @return
   */
  public static int Sign(Vector2 vector1, Vector2 vector2) {
    if (vector1.y * vector2.x > vector1.x * vector2.y) {
      return -1;
    } else {
      return 1;
    }
  }

  /**
   * Returns a vector perpendicular to this vector
   *
   * @param vector
   * @return
   */
  public static Vector2 Perpendicular(Vector2 vector) {
    return new Vector2(-1 * vector.y, vector.x);
  }
}
