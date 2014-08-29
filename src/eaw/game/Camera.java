package eaw.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

public class Camera {
  public final OrthographicCamera orthographicCamera;
  private final Vector3 targetPosition = new Vector3(0.0f, 0.0f, 1.0f);

  private Vector3 mouseWorldPosition = new Vector3();
  private Vector2 mouseWorld2Output = new Vector2();

  public float
    zMax = 4.0f,
    zMin = .125f;

  public Camera(Point viewSize) {
    orthographicCamera = new OrthographicCamera(viewSize.x, viewSize.y);
    orthographicCamera.setToOrtho(false);
    orthographicCamera.translate(-viewSize.x / 2, -viewSize.y / 2);
  }

  public void addMoveScaled(float x, float y, float z) {
    targetPosition.x += x * targetPosition.z;
    targetPosition.y += y * targetPosition.z;
    targetPosition.z += z;
  }

  public void update(float deltaTime) {
    final float smooth = .25f;

    targetPosition.z = Math.min(Math.max(targetPosition.z, zMin), zMax);

    orthographicCamera.position.x += (targetPosition.x - orthographicCamera.position.x) * smooth * deltaTime;
    orthographicCamera.position.y += (targetPosition.y - orthographicCamera.position.y) * smooth * deltaTime;
    orthographicCamera.zoom += (targetPosition.z - orthographicCamera.zoom) * smooth * deltaTime;

    orthographicCamera.update();

    mouseWorldPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0.0f);
    orthographicCamera.unproject(mouseWorldPosition);
    mouseWorld2Output.set(mouseWorldPosition.x, mouseWorldPosition.y);
  }

  public Vector2 getMouseWorldPosition() {
    return mouseWorld2Output;
  }
}
