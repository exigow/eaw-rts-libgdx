package eaw.game.mechanics;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Definition implements Json.Serializable {

    public String name;
    public float[] vertices;
    public int imageHullId;

    private static String NAME = "name", IMAGE = "imageHullId", VERTS = "vertices";

    public void write (Json json) {
        json.writeValue(NAME, name);
        json.writeValue(IMAGE, imageHullId);
        json.writeValue(VERTS, vertices);
    }

    public void read (Json json, JsonValue jsonMap) {
        name = json.readValue(NAME, String.class, jsonMap);
        imageHullId = json.readValue(IMAGE, Integer.class, jsonMap);
        vertices = json.readValue(VERTS, float[].class, jsonMap);
    }
}
