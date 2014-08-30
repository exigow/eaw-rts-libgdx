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

    if (inputs.cameraUp.isPressed()) {
      System.out.println("left is pressed");
    }

    if (inputs.cameraUp.isReleased()) {
      System.out.println("left is released");
    }

    if (inputs.mouseLeft.isReleased()) {
      System.out.println("left is released");
    }

  }

}
