package eaw;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import eaw.game.Camera;
import eaw.game.GameController;
import eaw.game.World;
import eaw.game.WorldRenderer;
import eaw.game.assets.Assets;
import eaw.game.mechanics.Definition;
import eaw.game.mechanics.Group;
import eaw.game.mechanics.Unit;
import eaw.game.utilities.DrawingProvider;
import eaw.game.utilities.MathUtilities;

import java.awt.*;

public class Main implements ApplicationListener {

  float deltaTime, time = 0;
  Camera camera;
  GameController controller;
  DrawingProvider drawingProvider;
  WorldRenderer worldRenderer;
  World world;
  Matrix4 screenMatrix;

  public void create() {
    final Json json = new Json();

    final Definition source = json.fromJson(Definition.class, Gdx.files.internal("assets/shipdef.json").readString());
    System.out.println(json.prettyPrint(source));

    world = new World();

    Point screenSize = new Point(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    worldRenderer = new WorldRenderer(world, screenSize);

    Assets assets = Assets.createFromJsonFile(json, Gdx.files.local("assets/images.json")).load();
    drawingProvider = new DrawingProvider(assets);

    camera = new Camera(screenSize);
    screenMatrix = new OrthographicCamera(screenSize.x, screenSize.y).combined.setTranslation(-1f, -1f, 0f);
    controller = new GameController();

    for (int g = 0; g < 4; g++) {
      final Group group = new Group();
      world.groups.add(group);
      Vector2 tempGroupPosition = new Vector2().set(MathUtilities.randn() * 512, MathUtilities.randn() * 512);
      for (int u = 0; u < 5; u++) {
        final Unit unit = new Unit(source);
        unit.place.position.set(tempGroupPosition);
        unit.place.position.add(MathUtilities.randn() * 128, MathUtilities.randn() * 128);
        group.units.add(unit);
      }
    }
  }

  public void render() {
    deltaTime = Gdx.graphics.getDeltaTime();
    time += deltaTime;

    controller.control(camera);
    controller.selecting(camera, world.groups);
    controller.action(camera, world.groups);

    camera.update(1.0f);


    worldRenderer.renderWorld(drawingProvider, camera.orthographicCamera.combined);


    drawingProvider.setProjection(screenMatrix);
    worldRenderer.draw(drawingProvider);

    drawingProvider.spriteBatch.begin();
    drawingProvider.assets.debugFont.drawMultiLine(drawingProvider.spriteBatch,
      "fps: " + Gdx.graphics.getFramesPerSecond() + "\n" +
      "highlightedList: " + controller.highlightedList + "\n" +
      "selectedList: " + controller.selectedList + "\n",
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