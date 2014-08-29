package eaw.game.mechanics.orders;

public abstract class Order {

  public abstract String getName();

  public abstract String getDetails();

  @Override
  public final String toString() {
    return "" + getName() + ": " + getDetails();
  }

}
