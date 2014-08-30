package eaw.game.input;


import com.badlogic.gdx.Gdx;

public class KeyboardButton extends Button {

  public KeyboardButton(int key) {
    super(key);
  }

  @Override
  protected boolean getSignal() {
    return Gdx.input.isKeyPressed(key);
  }

}
