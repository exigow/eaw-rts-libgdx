import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import eaw.Main;

public class Runner {
  public static void main(String[] args) {
    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = "<eaw>";
    cfg.width = 800;
    cfg.height = 600;
    cfg.resizable = false;
    new LwjglApplication(new Main(), cfg);
  }
}
