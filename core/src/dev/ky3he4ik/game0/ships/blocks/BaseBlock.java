package dev.ky3he4ik.game0.ships.blocks;

import com.badlogic.gdx.graphics.Texture;

import dev.ky3he4ik.game0.ships.BaseBullet;
import dev.ky3he4ik.game0.ships.BaseShip;

abstract public class BaseBlock {
    public final static int MAX_DURABILITY = 0;
    public final static int DURABILITY_REGEN_PER_TICK = 0;
    public final static BaseBullet BULLETS = null; // todo: bullet class
    public final static int ENERGY_CHANGE_PER_TICK = 0;
    public final static boolean ALWAYS_ACTIVE = false;
    public final static int THRUST_PER_TICK = 0;
    public final static int ROT_THRUST_PER_TICK = 0;
    public final static int WEIGTH = 0;
    public final static int TIMEOUT_TICKS = 0;
    public final static int SIZE_X = 0, SIZE_Y = 0;
    public final static int ENERGY_STORAGE = 0;
    protected Texture texture;
    protected String textureName;

    void specialAction(BaseShip ship) {
    }

    public int getAssignedKeyCode() {
        return assignedKeyCode;
    }

    public void setAssignedKeyCode(int assignedKeyCode) {
        this.assignedKeyCode = assignedKeyCode;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseBlock)) return false;

        BaseBlock baseBlock = (BaseBlock) o;

        if (assignedKeyCode != baseBlock.assignedKeyCode) return false;
        return (durability == baseBlock.durability);
    }

    @Override
    public int hashCode() {
        int result = assignedKeyCode;
        result = (result << 8) + durability;
        return result;
    }

    private int assignedKeyCode;
    private int durability;

    BaseBlock(String textureName, int _assignedKeyCode) {
        this.textureName = textureName;
        durability = MAX_DURABILITY;
        assignedKeyCode = _assignedKeyCode;
    }

    BaseBlock(String textureName) {
        this.textureName = textureName;
        durability = MAX_DURABILITY;
        assignedKeyCode = -1;
    }

    public Texture getTexture() {
        if (texture == null)
            texture = new Texture(textureName);
//            texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        return texture;
    }

    public BaseBlock toBaseBlock() { return this; }

    public void dispose() {
        if (texture != null) {
            texture.dispose();
            texture = null;
        }
    }

}
