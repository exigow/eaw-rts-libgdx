package eaw.game.mechanics;

import eaw.game.Camera;
import eaw.game.World;
import eaw.game.input.Inputs;

public class Controller {

  private final World world;
  private final Camera camera;
  private final Inputs inputs;

  public Controller(World world, Camera camera) {
    this.world = world;
    this.camera = camera;
    inputs = new Inputs();
  }

  public void execute(float deltaTime) {
    camera.update(1.0f);
    inputs.update();

    float planeSpeed = 16.0f * deltaTime,
      depthSpeed = .075f * deltaTime;
    camera.addMoveScaled(
      cameraKeyVectorControl(
        inputs.cameraRight.isHeld(),
        inputs.cameraLeft.isHeld()
      ) * planeSpeed,
      cameraKeyVectorControl(
        inputs.cameraUp.isHeld(),
        inputs.cameraDown.isHeld()
      ) * planeSpeed,
      cameraKeyVectorControl(
        inputs.cameraOut.isHeld(),
        inputs.cameraIn.isHeld()
      ) * depthSpeed
    );
  }

  private float cameraKeyVectorControl(boolean plus, boolean minus) {
    if (plus && minus)
      return 0;
    if (plus)
      return 1;
    if (minus)
      return -1;
    return 0;
  }

}
