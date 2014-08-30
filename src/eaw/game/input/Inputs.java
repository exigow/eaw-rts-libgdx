package eaw.game.input;


import com.badlogic.gdx.Input;

public class Inputs {

  public final Button
    mouseLeft = new MouseButton(Input.Buttons.LEFT),
    mouseRight = new MouseButton(Input.Buttons.RIGHT),

    cameraUp = new KeyboardButton(Input.Keys.W),
    cameraDown = new KeyboardButton(Input.Keys.S),
    cameraLeft = new KeyboardButton(Input.Keys.A),
    cameraRight = new KeyboardButton(Input.Keys.D),
    cameraIn = new KeyboardButton(Input.Keys.E),
    cameraOut = new KeyboardButton(Input.Keys.Q);

  public void update() {
    mouseLeft.update();
    mouseRight.update();

    cameraUp.update();
    cameraDown.update();
    cameraLeft.update();
    cameraRight.update();
    cameraIn.update();
    cameraOut.update();
  }

}
