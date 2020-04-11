package dev.ky3he4ik.game0;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//import com.badlogic.gdx.graphics.Texture;
//add this import and NOT the one in the standard library

public class MyGdxGame extends Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;

    public SpriteBatch batch;
    public BitmapFont font;

    public final static int CELL_SIZE = 21;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        this.setScreen(new MainScreen(this));
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void render() {
        super.render();
    }


    @Override
    public void dispose() {
        // dispose of all the native resources
        batch.dispose();
        font.dispose();
    }
}
