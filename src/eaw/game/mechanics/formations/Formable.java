package eaw.game.mechanics.formations;

import eaw.game.mechanics.Place;

public interface Formable {

  public int getImportance();

  public void setTarget(Place target);

  public Place getPlace();

}
