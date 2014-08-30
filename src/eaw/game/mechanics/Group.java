package eaw.game.mechanics;

import eaw.game.Drawable;
import eaw.game.Mutable;
import eaw.game.mechanics.formations.Formation;
import eaw.game.mechanics.formations.SnakeFormation;
import eaw.game.mechanics.orders.Order;
import eaw.game.utilities.DrawingProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Group extends Identifiable implements Drawable, Mutable {

  public final List<Unit> units = new ArrayList<Unit>();
  public final Stack<Order> orders = new Stack<Order>();
  private Formation formation = new SnakeFormation();

  @Override
  public void draw(DrawingProvider provider) {
    for (Drawable drawableUnit : units)
      drawableUnit.draw(provider);
  }

  public Unit getHead() {
    return units.get(0);
  }

  public String toString() {
    return "units: " + units + ", orders: " + orders + "";
  }

  @Override
  public void update(float deltaTime) {
    formation.format(units);
    for (Unit unit : units)
      unit.update(deltaTime);
  }

  // todo execute order is for head (movement)

}
