package eaw.game.mechanics;

import eaw.game.Drawable;
import eaw.game.mechanics.orders.Order;
import eaw.game.utilities.DrawingProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Group extends Identifiable implements Drawable {

  public final List<Unit> units = new ArrayList<Unit>();
  public final Stack<Order> orders = new Stack<Order>();

  @Override
  public void draw(DrawingProvider provider) {
    for (Drawable drawableUnit : units)
      drawableUnit.draw(provider);
  }

  public String toString() {
    return "units: " + units + ", orders: " + orders + "";
  }

}
