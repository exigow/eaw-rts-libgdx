package eaw.game.mechanics;

import com.badlogic.gdx.math.Vector2;
import eaw.game.Drawable;
import eaw.game.mechanics.orders.Order;
import eaw.game.utilities.DrawingProvider;

import java.util.ArrayList;
import java.util.List;

public class Group extends Identifiable implements Drawable {

  public final ArrayList<Unit> units = new ArrayList<Unit>();
  public final List<Order> orders = new ArrayList<Order>();
  public final Vector2 center = new Vector2();

  @Override
  public void draw(DrawingProvider provider) {
    for (Drawable drawableUnit : units)
      drawableUnit.draw(provider);
  }

  public String toString() {
    return "units: " + units + ", orders: " + orders + "";
  }

}
