package eaw.game.mechanics;

import eaw.game.Drawable;
import eaw.game.utilities.DrawingProvider;

public class Unit extends Identifiable implements Drawable {

  public final Place place = new Place();
  public final Definition definition;
  public float size = 16.0f;

  public Unit(Definition definition) {
    this.definition = definition;
  }

  @Override
  public void draw(DrawingProvider provider) {
    provider.spriteBatch.begin();
    provider.assets.getImage(definition.imageHullId).draw(provider.spriteBatch, place.position, place.direction);
    provider.spriteBatch.end();

    place.draw(provider);
  }

}