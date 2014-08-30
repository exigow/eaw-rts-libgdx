package eaw.game.mechanics;

import com.badlogic.gdx.Gdx;

public class Key {

  private enum State {
    WAIT, PRESS, HOLD, RELEASE
  }

  private State actualStep = State.WAIT;
  private boolean changeBoolean = false;
  private final int gdxKey;

  public Key(int gdxKey) {
    this.gdxKey = gdxKey;
  }

  public void update() {
    boolean source = Gdx.input.isKeyPressed(gdxKey);
    changeBoolean = false;
    if (source) {
      if (actualStep == State.PRESS) {
        changeTo(State.HOLD);
      }
      if (actualStep == State.WAIT) {
        changeTo(State.PRESS);
      }
    } else {
      if (actualStep == State.RELEASE) {
        changeTo(State.WAIT);
      }
      if (actualStep == State.HOLD) {
        changeTo(State.RELEASE);
      }
    }
  }

  private void changeTo(State step) {
    actualStep = step;
    changeBoolean = true;
  }

  public boolean isPressed() {
    return actualStep == State.PRESS;
  }

  public boolean isHeld() {
    return actualStep == State.HOLD;
  }

  public boolean isReleased() {
    return actualStep == State.RELEASE;
  }

}
