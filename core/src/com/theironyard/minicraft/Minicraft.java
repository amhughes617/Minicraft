package com.theironyard.minicraft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Minicraft extends ApplicationAdapter {
    SpriteBatch batch;
    TextureRegion down, up, right, stand, left, downF, upF;
    Animation walkX, walkUp, walkDown;
    static final int WIDTH = 18;
    static final int HEIGHT = 26;
    static final float MAX_VELOCITY = 100;
    float x, y, xv, yv, time;
    boolean faceRight = true;
    FitViewport viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    @Override
    public void create () {
        batch = new SpriteBatch();

        Texture tiles = new Texture("tiles.png");
        TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
        down = grid[6][0];
        downF = new TextureRegion(down);
        downF.flip(true, false);
        up = grid[6][1];
        right = grid[6][3];
        stand = grid[6][2];
        left = new TextureRegion(right);
        left.flip(true, false);
        upF = new TextureRegion(up);
        upF.flip(true, false);
        walkX = new Animation(0.1f, right, stand);
        walkUp = new Animation(0.1f, up, upF);
        walkDown = new Animation(0.1f, down, downF);
    }

    @Override
    public void render () {

        time += Gdx.graphics.getDeltaTime();

        move();

        TextureRegion img;
        if (yv > 0) {
            img = walkUp.getKeyFrame(time, true);
        }
        else if (yv < 0) {
            img = walkDown.getKeyFrame(time, true);
        }
        else if (xv != 0) {
            img = walkX.getKeyFrame(time, true);
        }
        else {
            img = stand;
        }

        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        if (faceRight) {
            batch.draw(img, x, y, WIDTH, HEIGHT);
        }
        else {
            batch.draw(img, WIDTH + x, y, -1 * WIDTH, HEIGHT);
        }
        batch.end();
    }
    float decelerate(float v, float deceleration) { //closer to 1 the slower the decelaration
        v *= deceleration;
        if (Math.abs(v) < 1) v = 0f;
        return v;
    }

    void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yv = MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yv = -1 * MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xv = MAX_VELOCITY;
            faceRight = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xv = MAX_VELOCITY * -1;
            faceRight = false;
        }
        y += yv * Gdx.graphics.getDeltaTime();
        x += xv * Gdx.graphics.getDeltaTime();

        if (y < 0) {
            y = 0;
        }
        if (y > viewport.getScreenHeight()) {
            y = viewport.getScreenHeight();
        }
        if (x < 0) {
            x = 0;
        }
        if (x > viewport.getScreenWidth()) {
            x = viewport.getScreenWidth();
        }


        yv = decelerate(yv, 0.5f);
        xv = decelerate(xv, 0.5f);
    }
}
