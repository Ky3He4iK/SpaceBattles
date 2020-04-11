package dev.ky3he4ik.game0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import dev.ky3he4ik.game0.ships.SimpeShip;

public class GameScreen implements Screen {
    private final MyGdxGame game;
    private static final float scapeStep = 1.05f;

    private OrthographicCamera camera;
    private SimpeShip ship;
    private Texture background;
    private float scale = 1;

    GameScreen(final MyGdxGame game) {
        this.game = game;

        background = new Texture("Background_v01.jpg");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        game.batch = new SpriteBatch();
//		ship = new BaseShip(3, 3, 0, 0, 0);
//		img = new Texture("badlogic.jpg");
        ship = new SimpeShip(-background.getWidth() / 2, -background.getHeight() / 2, -(float)Math.PI / 2);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        float deltaTime = Gdx.graphics.getDeltaTime();
        if (deltaTime > 0.05f) // if less then 20 FPS
            deltaTime = 0.05f; // act like 20 FPS

        ship.move(deltaTime, Gdx.input.isKeyPressed(Input.Keys.RIGHT), Gdx.input.isKeyPressed(Input.Keys.LEFT),
                Gdx.input.isKeyPressed(Input.Keys.UP), Gdx.input.isKeyPressed(Input.Keys.DOWN), Gdx.input.isKeyPressed(Input.Keys.SPACE));


        if (Gdx.input.isKeyPressed(Input.Keys.Q))
            scale *= scapeStep;
        if (Gdx.input.isKeyPressed(Input.Keys.E))
            scale /= scapeStep;

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        game.batch.begin();
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.draw(background, ship.getX(), ship.getY());

        ship.render(game.batch, scale);

        game.font.setColor(1, 1, 0.5f, 1);
        game.font.draw(game.batch, "FPS: " + Gdx.graphics.getFramesPerSecond() + "\nCoord: (" + ship.getX() + "; " + ship.getY() +
                        ")\nAngle: " + ship.getAngle() + " (" + ship.getAngle() / (float) Math.PI * 180 +
                        ")\nSpeed: (" + ship.getSpeedX() + "; " + ship.getSpeedY() + ";   Rot: " + ship.getAngleSpeed() +
                        ")\nScale: " + scale +
                        "Bg size: " + background.getWidth() + "x" + background.getHeight()
                , 0, camera.viewportHeight - game.font.getCapHeight());

        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
        }

        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.debug("GameScreen", "Back button pressed");
            game.setScreen(new MainScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        ship.dispose();
        background.dispose();
    }
}
