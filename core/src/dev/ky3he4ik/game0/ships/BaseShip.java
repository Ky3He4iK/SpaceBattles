package dev.ky3he4ik.game0.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


//import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dev.ky3he4ik.game0.MyGdxGame;
import dev.ky3he4ik.game0.ships.blocks.BaseBlock;
import dev.ky3he4ik.game0.ships.blocks.TinyCore;

public class BaseShip {
    private float x, y, angle;
    private float speedPerTick, rotationSpeedPerTick;
    private int energyStorage;
    private int currentEnergy;

    private int height, width;

    private int actual_h_begin, actual_h;
    private int actual_w_begin, actual_w;
    private int center_x, center_y;

    private ArrayList<BaseBlock> components = new ArrayList<>();
    private ArrayList<Sprite> componentSprites = new ArrayList<>();

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getSpeedPerTick() {
        return speedPerTick;
    }

    public void setSpeedPerTick(float speedPerTick) {
        this.speedPerTick = speedPerTick;
    }

    public float getRotationSpeedPerTick() {
        return rotationSpeedPerTick;
    }

    public void setRotationSpeedPerTick(float rotationSpeedPerTick) {
        this.rotationSpeedPerTick = rotationSpeedPerTick;
    }

    public int getEnergyStorage() {
        return energyStorage;
    }

    public void setEnergyStorage(int energyStorage) {
        this.energyStorage = energyStorage;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getActual_h_begin() {
        return actual_h_begin;
    }

    public void setActual_h_begin(int actual_h_begin) {
        this.actual_h_begin = actual_h_begin;
    }

    public int getActual_h() {
        return actual_h;
    }

    public void setActual_h(int actual_h) {
        this.actual_h = actual_h;
    }

    public int getActual_w_begin() {
        return actual_w_begin;
    }

    public void setActual_w_begin(int actual_w_begin) {
        this.actual_w_begin = actual_w_begin;
    }

    public int getActual_w() {
        return actual_w;
    }

    public void setActual_w(int actual_w) {
        this.actual_w = actual_w;
    }

    public ArrayList<BaseBlock> getComponents() {
        return components;
    }

    public ArrayList<ArrayList<Integer>> getComponentsGrid() {
        return componentsGrid;
    }

    private ArrayList<ArrayList<Integer>> componentsGrid;

    public BaseShip(int _height, int _width, float _x, float _y, float _angle) {
        if (_height % 2 == 0 || _width % 2 == 0 || _height < 2 || _width < 2)
            Gdx.app.error("ShipConstructor", "Wrong dimensions given to BaseShip: (" + _height + "; " + _width + "). Stability is not guaranteed");

        height = _height;
        width = _width;
        x = _x;
        y = _y;
        center_x = width / 2;
        center_y = height / 2;
        angle = _angle;
        speedPerTick = 0;
        rotationSpeedPerTick = 0;
        componentsGrid = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            componentsGrid.add(new ArrayList<Integer>(width));
            for (int j = 0; j < width; j++) {
                if (i == center_y && j == center_x)
                    componentsGrid.get(i).add(0);
                else
                    componentsGrid.get(i).add(-1);
            }
        }
        TinyCore core = new TinyCore();
        components.add(core);
        Sprite coreSprite = new Sprite(TinyCore.getTexture());

        //centre of rotation
        coreSprite.setOrigin(coreSprite.getWidth() / 2, coreSprite.getHeight() / 2);
        // for (i, j):
//        int i = 0, j = 0;
//        coreSprite.setOrigin((i - center_y) * MyGdxGame.CELL_SIZE, (j - center_x) * MyGdxGame.CELL_SIZE);

        componentSprites.add(coreSprite);
        actual_h = 1;
        actual_w = 1;
        actual_h_begin = height / 2;
        actual_w_begin = width / 2;

        energyStorage = TinyCore.ENERGY_STORAGE;
        currentEnergy = TinyCore.ENERGY_STORAGE;
    }

    public final void render(/*@NotNull */SpriteBatch batch, float cornerX, float cornerY, int screenSizeX, int screenSizeY, float scale) {
        float center_pixel_x = (cornerX - x) * scale;
        float center_pixel_y = (cornerY - y) * scale;

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                if (componentsGrid.get(i).get(j) != -1) {
                    Sprite currSprite = componentSprites.get(componentsGrid.get(i).get(j));
                    currSprite.setRotation(45);
                    currSprite.setPosition(center_pixel_x + (j - center_x) * MyGdxGame.CELL_SIZE * scale,
                            center_pixel_y + (i - center_y) * MyGdxGame.CELL_SIZE * scale);
                    currSprite.draw(batch);
//                    batch.draw(componentSprites.get(componentsGrid.get(i).get(j)), 0, 0);
                }

    }

    public void dispose() {
        for (BaseBlock block: components)
            block.dispose();
    }
}
