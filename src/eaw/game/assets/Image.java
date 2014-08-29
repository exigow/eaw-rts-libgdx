package eaw.game.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Image implements Json.Serializable {
    public Texture texture;
    public Vector2 center;
    public String filename;
    public int id;

    public Image() {
    }

    public void write (Json json) {
        json.writeValue("id", id);
        json.writeValue("filename", filename);
        json.writeValue("center", center);
    }

    public void read (Json json, JsonValue jsonMap) {
        id = json.readValue("id", Integer.class, jsonMap);
        filename = json.readValue("filename", String.class, jsonMap);
        center = json.readValue("center", Vector2.class, jsonMap);
    }

    public void setup() {
        center.y = texture.getHeight() - center.y;
    }

    private Vector2 fixedDrawPosition = new Vector2();
    public void draw(SpriteBatch batch, Vector2 position, float direction) {
        fixedDrawPosition.set(position);
        fixedDrawPosition.x -= Math.cos(direction) * center.x + Math.cos(direction + Math.PI / 2) * center.y;
        fixedDrawPosition.y -= Math.sin(direction) * center.x + Math.sin(direction + Math.PI / 2) * center.y;

        batch.draw(texture,
            fixedDrawPosition.x, fixedDrawPosition.y,
            0, 0,
            texture.getWidth(), texture.getHeight(),
            1, 1,
            (float)Math.toDegrees(direction),
            0, 0,
            texture.getWidth(), texture.getHeight(),
            false, false);
    }

}
