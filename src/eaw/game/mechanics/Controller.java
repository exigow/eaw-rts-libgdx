package eaw.game.mechanics;

import com.badlogic.gdx.math.Vector3;
import eaw.game.Camera;
import eaw.game.World;
import eaw.game.input.Inputs;

public class Controller {

  private final World world;
  private final Camera camera;
  private final Inputs inputs;

  private Action action;
  private enum Action {
    SELECTING
  }

  public Controller(World world, Camera camera) {
    this.world = world;
    this.camera = camera;
    inputs = new Inputs();
    action = null;
  }

  public void execute(float deltaTime) {
    inputs.update();
    camera.addMoveScaled(getCameraMovement(deltaTime));
    camera.update(1.0f);
  }

  private Vector3 getCameraMovement(float deltaTime) {
    float planeSpeed = 16.0f * deltaTime,
      depthSpeed = .075f * deltaTime;
    return new Vector3(
      oppositeKeysToVector(
      inputs.cameraRight.isHeld(),
      inputs.cameraLeft.isHeld()
      ) * planeSpeed,
      oppositeKeysToVector(
        inputs.cameraUp.isHeld(),
        inputs.cameraDown.isHeld()
      ) * planeSpeed,
      oppositeKeysToVector(
        inputs.cameraOut.isHeld(),
        inputs.cameraIn.isHeld()
      ) * depthSpeed);
  }

  private float oppositeKeysToVector(boolean positive, boolean negative) {
    if (positive && negative)
      return 0;
    if (positive)
      return 1;
    if (negative)
      return -1;
    return 0;
  }

}
