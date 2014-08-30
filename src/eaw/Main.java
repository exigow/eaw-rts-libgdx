package eaw;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.sun.glass.ui.Size;
import eaw.game.Camera;
import eaw.game.World;
import eaw.game.WorldRenderer;
import eaw.game.assets.Assets;
import eaw.game.mechanics.Controller;
import eaw.game.mechanics.Definition;
import eaw.game.mechanics.Group;
import eaw.game.mechanics.Unit;
import eaw.game.utilities.DrawingProvider;
import eaw.game.utilities.MathUtilities;

public class Main implements ApplicationListener {

  float deltaTime, time = 0;
  Camera camera;
  //_GameController controller;
  Controller controller;
  DrawingProvider drawingProvider;
  WorldRenderer worldRenderer;
  World world;
  Matrix4 screenMatrix;

  public void create() {
    Json json = new Json();
    Definition source = json.fromJson(Definition.class, Gdx.files.internal("assets/shipdef.json").readString());
    world = new World();
    Size screenSize = new Size(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    camera = new Camera(screenSize);
    worldRenderer = new WorldRenderer(world, screenSize);
    controller = new Controller(world, camera);
    Assets assets = Assets.createFromJsonFile(json, Gdx.files.internal("assets/images.json")).load();
    drawingProvider = new DrawingProvider(assets);
    screenMatrix = new OrthographicCamera(screenSize.width, screenSize.height).combined.setTranslation(-1f, -1f, 0f);

    for (int g = 0; g < 4; g++) {
      Group group = new Group();
      world.groups.add(group);
      Vector2 tempGroupPosition = new Vector2().set(MathUtilities.randn() * 512, MathUtilities.randn() * 512);
      for (int u = 0; u < 5; u++) {
        Unit unit = new Unit(source);
        unit.place.position.set(tempGroupPosition);
        unit.place.position.add(MathUtilities.randn() * 128, MathUtilities.randn() * 128);
        unit.target.set(unit.place);
        group.units.add(unit);
      }
    }
  }

  public void render() {
    deltaTime = Gdx.graphics.getDeltaTime();
    time += deltaTime;

    //controller.control(camera);
    //controller.selecting(camera, world.groups);
    //controller.action(camera, world.groups);
    world.update(1.0f);

    controller.execute(1.0f);

    worldRenderer.renderWorld(drawingProvider, camera.orthographicCamera.combined);

    drawingProvider.setProjection(screenMatrix);
    worldRenderer.draw(drawingProvider);

    drawingProvider.spriteBatch.begin();
    drawingProvider.assets.debugFont.drawMultiLine(drawingProvider.spriteBatch,
      "fps: " + Gdx.graphics.getFramesPerSecond() + "\n" +
      /*"highlightedList: " + controller.highlightedList + "\n" +
      "selectedList: " + controller.selectedList + "\n",*/ "",
      32, Gdx.graphics.getHeight() - 32);
    drawingProvider.spriteBatch.end();
  }

  public void resize(int width, int height) {
  }

  public void pause() {
  }

  public void resume() {
  }

  public void dispose() {
  }

}