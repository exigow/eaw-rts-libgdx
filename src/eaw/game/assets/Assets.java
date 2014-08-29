package eaw.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Assets implements Json.Serializable {

  public BitmapFont debugFont;
  private List<Image> list;
  private final HashMap<Integer, Image> map;
  private String subPath;

  public Assets() {
    list = new ArrayList<Image>();
    map = new HashMap<Integer, Image>();
    debugFont = new BitmapFont(Gdx.files.internal("assets/arial_16px.fnt"), Gdx.files.internal("assets/arial_16px_0.png"), false);
  }

  public static Assets createFromJsonFile(Json json, FileHandle file) {
    return json.fromJson(Assets.class, file);
  }

  public void write (Json json) {
    json.writeValue("subPath", subPath);
    json.writeValue("list", list);
  }

  public void read (Json json, JsonValue jsonMap) {
    subPath = json.readValue("subPath", String.class, jsonMap);
    list = json.readValue("list", ArrayList.class, jsonMap);
  }

  public Assets load() {
    for (Image image: list) {
      image.texture = new Texture(Gdx.files.internal(subPath + image.filename));
      image.setup();
      map.put(image.id, image);
    }
    return this;
  }

  public Image getImage(int id) {
    return map.get(id);
  }

}
