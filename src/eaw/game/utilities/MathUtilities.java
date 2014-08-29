package eaw.game.utilities;

import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.*;

public class MathUtilities {
  /**
   * @return random value between -1 and 1
   */
  public static float randn() {
      return -1 + (float)Math.random() * 2;
  }

  /**
   * Returns direction between two 2d vectors.
   * @param vecA "from" vector
   * @param vectB "to" vector
   * @return direction (degrees)
   */
  public static float pointDirection(Vector2 vecA, Vector2 vectB) {
    return pointDirection(vecA.x, vecA.y, vectB.x, vectB.y);
  }

  /**
   * Direction between A (from) and B (to).
   * @return direction (degrees)
   */
  public static float pointDirection(float Ax, float Ay, float Bx, float By) {
    return (float)(toDegrees(atan2(Ay - By, Ax - Bx)));
  }

  /**
   * Returns distance between two 2d vectors.
   * @return distance
   */
  public static float pointDistance(Vector2 a, Vector2 b) {
    return pointDistance(a.x, a.y, b.x, b.y);
  }

  /**
   * Distance between A and B.
   */
  public static float pointDistance(float ax, float ay, float bx, float by) {
    return (float)sqrt(pow((bx - ax), 2) + pow((by - ay), 2));
  }

  /**
   * Distance between rectangle and position.
   */
  public static float pointRectDistance(Vector2 point, Vector2 start, Vector2 end) {
    return pointRectDistance(point.x, point.y, start.x, start.y, end.x, end.y);
  }
  public static float pointRectDistance(float px, float py, float ex, float ey, float sx, float sy) {
    float cx = max(Math.min(px, sx), ex);
    float cy = max(Math.min(py, sy), ey);
    return (float)sqrt((px - cx) * (px - cx) + (py - cy) * (py - cy));
  }

  public static float ldx(float len, float dir) {
    return (float)(cos(toRadians(dir))) * len;
  }
  public static float ldy(float len, float dir) {
    return (float)(sin(toRadians(dir))) * len;
  }

  public static Vector2 positionPlusTranslated(Vector2 basePosition, Vector2 localPosition, float direction) {
    return new Vector2(
      basePosition.x
        + MathUtilities.ldx(localPosition.x, direction + 90)
        + MathUtilities.ldx(localPosition.y, direction),
      basePosition.y
        + MathUtilities.ldy(localPosition.x, direction + 90)
        + MathUtilities.ldy(localPosition.y, direction));
  }

  /**
   * Difference between two angles.
   * @param angle0 first angle
   * @param angle1 second angle
   * @return difference (degrees)
   */
  public static float angdiff(float angle0, float angle1) {
    return ((((angle0 - angle1) % 360) + 540) % 360) - 180;
  }

  /**
   * Linear interpolation.
   * @param from first value
   * @param to second value
   * @param percent level of interpolation (0 - 1)
   * @return
   */
  public static float lerp(float from, float to, float percent) {
    return from * (1 - percent) + to * percent;
  }

  /**
   * Linear interpolation between two vectors.
   */
  public static void lerpVectors(Vector2 sendTo, Vector2 vecA, Vector2 vecB, float percent) {
    sendTo.set(
      lerp(vecA.x, vecB.x, percent),
      lerp(vecA.y, vecB.y, percent)
    );
  }
}
