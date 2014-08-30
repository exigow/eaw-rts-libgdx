package eaw.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sun.glass.ui.Size;

public class Camera {
  public final OrthographicCamera orthographicCamera;
  private final Vector3 targetPosition = new Vector3(0.0f, 0.0f, 1.0f);

  private Vector3 mouseWorldPosition = new Vector3();
  private Vector2 mouseWorld2Output = new Vector2();

  public float
    zMax = 4.0f,
    zMin = .125f;

  public Camera(Size viewSize) {
    orthographicCamera = new OrthographicCamera(viewSize.width, viewSize.height);
    orthographicCamera.setToOrtho(false);
    orthographicCamera.translate(-viewSize.width / 2, -viewSize.height / 2);
  }

  public void addMoveScaled(Vector3 vec) {
    targetPosition.add(
      vec.x * targetPosition.z,
      vec.y * targetPosition.z,
      vec.z
    );
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
