package eaw.game.mechanics.orders;

import eaw.game.mechanics.Place;

public class MoveOrder extends Order {

  private final Place moveTarget;

  public MoveOrder(Place moveTarget) {
    this.moveTarget = moveTarget;
  }

  @Override
  public String getName() {
    return "MOVE";
  }

  @Override
  public String getDetails() {
    return moveTarget.toString();
  }

}
