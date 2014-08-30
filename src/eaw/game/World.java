package eaw.game;

import eaw.game.mechanics.Group;
import eaw.game.utilities.DrawingProvider;

import java.util.ArrayList;

public class World implements Drawable, Mutable {

  public final ArrayList<Group> groups = new ArrayList<Group>();

  @Override
  public void draw(DrawingProvider provider) {
    for (Drawable drawableGroup : groups)
      drawableGroup.draw(provider);
  }

  @Override
  public void update(float deltaTime) {
    for (Mutable group : groups)
      group.update(deltaTime);
  }

}
