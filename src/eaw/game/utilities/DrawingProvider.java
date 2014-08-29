package eaw.game.utilities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import eaw.game.assets.Assets;

public class DrawingProvider {

  public final Assets assets;
  public final SpriteBatch spriteBatch = new SpriteBatch();
  public final ShapeRenderer shapeRenderer = new ShapeRenderer();

  public DrawingProvider(Assets assets) {
    this.assets = assets;
  }

  public Matrix4 setProjection(Matrix4 projection) {
    spriteBatch.setProjectionMatrix(projection);
    shapeRenderer.setProjectionMatrix(projection);
    return projection;
  }

}
