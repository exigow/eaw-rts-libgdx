package eaw.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.sun.glass.ui.Size;
import eaw.game.utilities.DrawingProvider;

public class WorldRenderer implements Drawable {

  private final World world;
  private FrameBuffer mainFBO;

  public WorldRenderer(World world, Size size) {
    this.world = world;
    mainFBO = new FrameBuffer(Pixmap.Format.RGB888, size.width, size.height, false);
  }

  public void renderWorld(DrawingProvider renderProvider, Matrix4 matrix) {
    renderProvider.setProjection(matrix);
    mainFBO.begin();
    clearBuffer();
    world.draw(renderProvider);
    mainFBO.end();
  }

  private void clearBuffer() {
    Gdx.gl20.glClearColor(.075f, .075f, .075f, 1.0f);
    Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  @Override
  public void draw(DrawingProvider provider) {
    provider.spriteBatch.begin();
    provider.spriteBatch.draw(mainFBO.getColorBufferTexture(), 0, mainFBO.getHeight(), mainFBO.getWidth(), -mainFBO.getHeight());
    provider.spriteBatch.end();
  }

}