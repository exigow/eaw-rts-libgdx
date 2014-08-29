package eaw.game.mechanics;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import eaw.game.Drawable;
import eaw.game.utilities.DrawingProvider;

public class Place implements Drawable {

  public final Vector2 position;
  public float direction;

  public Place() {
    direction = (float)(Math.random() * Math.PI * 2);
    position = new Vector2();
  }

  public void set(Place place) {
    this.position.set(place.position);
    this.direction = place.direction;
  }

  public Place setPosition(Vector2 vector) {
    position.set(vector);
    return this;
  }

  @Override
  public void draw(DrawingProvider provider) {
    provider.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    int size = 16;
    provider.shapeRenderer.line(position.x, position.y,
      position.x + (float)Math.cos(direction) * size * 2,
      position.y + (float)Math.sin(direction) * size * 2);
    provider.shapeRenderer.line(position.x - size, position.y, position.x + size, position.y);
    provider.shapeRenderer.line(position.x , position.y- size, position.x, position.y + size);
    provider.shapeRenderer.end();
  }

  @Override
  public String toString() {
    return "[" + position + ", " + direction + "]";
  }

}

