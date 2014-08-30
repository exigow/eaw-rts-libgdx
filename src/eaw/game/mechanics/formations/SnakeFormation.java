package eaw.game.mechanics.formations;

import java.util.List;

public class SnakeFormation implements Formation {

  @Override
  public void format(List<? extends Formable> units) {
    for (int i = 1; i < units.size(); i++) {
      units.get(i).setTarget(units.get(i - 1).getPlace());
    }
  }

}
