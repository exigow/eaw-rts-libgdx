package eaw.game.mechanics;

import eaw.game.Drawable;
import eaw.game.Mutable;
import eaw.game.mechanics.formations.Formable;
import eaw.game.utilities.DrawingProvider;
import eaw.game.utilities.MathUtilities;

public class Unit extends Identifiable implements Drawable, Mutable, Formable {

  public final Place place = new Place(), target = new Place();
  public final Definition definition;
  public float size = 16.0f;

  public Unit(Definition definition) {
    this.definition = definition;
  }

  @Override
  public void update(float deltaTime) {
    place.direction += MathUtilities.angdiff(target.direction, place.direction) * .0125;
    place.position.x += (target.position.x - place.position.x) * .0125;
    place.position.y += (target.position.y - place.position.y) * .0125;
  }

  @Override
  public void draw(DrawingProvider provider) {
    provider.spriteBatch.begin();
    provider.assets.getImage(definition.imageHullId).draw(provider.spriteBatch, place.position, place.direction);
    provider.spriteBatch.end();
    place.draw(provider);
    target.draw(provider);
  }

  @Override
  public int getImportance() {
    return 1;
  }

  @Override
  public void setTarget(Place target) {
    this.target.set(target);
  }

  @Override
  public Place getPlace() {
    return place;
  }

}