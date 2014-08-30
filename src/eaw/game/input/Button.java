package eaw.game.input;

public abstract class Button {

  private enum State {
    WAIT, PRESS, HOLD, RELEASE
  }

  private State actualStep = State.WAIT;
  protected final int key;
  private boolean changed = false;

  public Button(int key) {
    this.key = key;
  }

  protected abstract boolean getSignal();

  public final void update() {
    boolean source = getSignal();
    changed = false;
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
    changed = true;
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
