package eaw.game.input;


import com.badlogic.gdx.Gdx;

public class MouseButton extends Button {

  public MouseButton(int key) {
    super(key);
  }

  @Override
  protected boolean getSignal() {
    return Gdx.input.isButtonPressed(key);
  }

}
