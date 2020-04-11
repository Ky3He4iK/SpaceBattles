package dev.ky3he4ik.game0.ships;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.ky3he4ik.game0.MyGdxGame;

public class SimpeShip {
    private final static float ACCELERATION = 5f;
    private final static float ANGLE_ACCELERATION = 5f;
    private float x = 0, y = 0, angle = 0;
    private float speedX = 0, speedY = 0, angleSpeed = 0;
    private final static String textureName = "cockpit_v02.png";
    private Texture texture = null;

    public SimpeShip() {
    }

    public SimpeShip(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public SimpeShip(float x, float y, float angle, float speedX, float speedY, float angleSpeed) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.angle = angle;
        this.angleSpeed = angleSpeed;
    }

    public void move(float deltaTime, boolean rotateRight, boolean rotateLeft, boolean accelerate, boolean decelerate, boolean stop) {

        if (stop) {
            angleSpeed = 0;
            speedX = 0;
            speedY = 0;
//            if (angleSpeed > 0) {
//                rotateRight = true;
//                rotateLeft = false;
//            } else if (angleSpeed < 0) {
//                rotateRight = false;
//                rotateLeft = true;
//            }
        }

        float acc = ANGLE_ACCELERATION * deltaTime;
        if (rotateRight)
            angleSpeed -= acc;
        else if (rotateLeft)
            angleSpeed += acc;
        if (Math.abs(angleSpeed) + 1e-6 < ANGLE_ACCELERATION * deltaTime)
            angleSpeed = 0;

        angle += angleSpeed * deltaTime;
        while (angle > Math.PI * 2)
            angle -= Math.PI * 2;
        while (angle < 0)
            angle += Math.PI * 2;

        if (accelerate) {
            speedX += ACCELERATION * (float) Math.cos(angle);
            speedY += ACCELERATION * (float) Math.sin(angle);
        }

//        if (Math.abs(speed) - ACCELERATION * deltaTime < 1e-5)
//            speed = 0;

        x += speedX * deltaTime;
        y += speedY * deltaTime;
    }

    public void render(SpriteBatch batch, float scale) {
        getTexture();
        batch.draw(texture, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2, texture.getWidth() / 2f,
                texture.getHeight() / 2f, texture.getWidth(), texture.getHeight(), scale, scale,
                angle / (float) Math.PI * 180, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    private Texture getTexture() {
        if (texture == null)
            texture = new Texture(textureName);
        return texture;
    }

    public void dispose() {
        if (texture != null) {
            texture.dispose();
            texture = null;
        }
    }

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

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speed) {
        this.speedX = speed;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getAngleSpeed() {
        return angleSpeed;
    }

    public void setAngleSpeed(float angleSpeed) {
        this.angleSpeed = angleSpeed;
    }
}
