package dev.ky3he4ik.game0.ships.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TinyCore extends BaseBlock {
    public final static int MAX_DURABILITY = 10;
    public final static int DURABILITY_REGEN_PER_TICK = 0;
    public final static int ENERGY_CHANGE_PER_TICK = 1;
    public final static boolean ALWAYS_ACTIVE = true;
    public final static int THRUST_PER_TICK = 1;
    public final static int ROT_THRUST_PER_TICK = 1;
    public final static int WEIGTH = 10;
    public final static int SIZE_X = 1, SIZE_Y = 1;
    public final static int ENERGY_STORAGE = 10;
    private static String textureName = "cockpit_v01.png";

    public static Texture getTexture() {
        if (texture == null)
            texture = new Texture(textureName);
            texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        return texture;
    }
}
