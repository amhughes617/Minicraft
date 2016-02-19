package com.theironyard.minicraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by alexanderhughes on 2/19/16.
 */
public class Player extends Dude {

    public void playerCreate() {

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
        // teleports the sprite to opposite side of screen
        if (y < (0 - HEIGHT)) {
            y = Gdx.graphics.getHeight();
        }
        if (y > Gdx.graphics.getHeight()) {
            y = 0 - HEIGHT;
        }
        if (x < 0 - WIDTH) {
            x = Gdx.graphics.getWidth();
        }
        if (x > Gdx.graphics.getWidth()) {
            x = 0 - WIDTH;
        }


        yv = decelerate(yv, 0.5f);
        xv = decelerate(xv, 0.5f);
    }

    float decelerate(float v, float deceleration) { //closer to 1 the slower the decelaration
        v *= deceleration;
        if (Math.abs(v) < 1) v = 0f;
        return v;
    }
}
