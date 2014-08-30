package eaw.game.mechanics;

import com.badlogic.gdx.Input;
import eaw.game.Camera;
import eaw.game.World;

import java.util.ArrayList;
import java.util.List;

public class Controller {

  final World world;
  final Camera camera;
  final Key
    keyRight,
    keyLeft;

  List<Key> keys = new ArrayList<Key>();

  private Key createKey(int gdxKey) {
    Key key = new Key(gdxKey);
    keys.add(key);
    return key;
  }

  public Controller(World world, Camera camera) {
    this.world = world;
    this.camera = camera;
    keyRight = createKey(Input.Keys.D);
    keyLeft = createKey(Input.Keys.A);
  }

  public void execute(float deltaTime) {
    camera.update(1.0f);
    for (Key key : keys) {
      key.update();
    }

    if (keyLeft.isPressed()) {
      System.out.println("left is pressed");
    }

    if (keyLeft.isReleased()) {
      System.out.println("left is released");
    }

  }

}
