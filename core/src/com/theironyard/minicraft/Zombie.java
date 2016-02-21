package com.theironyard.minicraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

/**
 * Created by alexanderhughes on 2/19/16.
 */
public class Zombie extends Dude {
    boolean moveUp, moveRight, movesV, movesH;
    public void enemyCreate() {

        Texture tiles = new Texture("tiles.png");
        TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
        down = grid[6][4];
        downF = new TextureRegion(down);

        downF.flip(true, false);
        up = grid[6][5];
        right = grid[6][7];
        stand = grid[6][6];
        left = new TextureRegion(right);

        left.flip(true, false);
        upF = new TextureRegion(up);
        upF.flip(true, false);

        walkX = new Animation(0.1f, right, stand);
        walkUp = new Animation(0.1f, up, upF);
        walkDown = new Animation(0.1f, down, downF);
    }

    void move(boolean canMove) {
        Minicraft.time2 += Gdx.graphics.getDeltaTime();
        Random r = new Random();
        if (canMove) {
            moveUp = r.nextBoolean();
            moveRight = r.nextBoolean();
            movesH = r.nextBoolean();
            movesV = r.nextBoolean();
            Minicraft.time2 = 0;
        }
        float minM = 100;
        float maxM = 500;
            float movement = r.nextFloat() * (maxM - minM) + minM;
            if (moveUp && movesV) {
                yv = MAX_VELOCITY;
            //    y += movement * Gdx.graphics.getDeltaTime();
            }
            else if (!moveUp && movesV) {
                yv = -1 * MAX_VELOCITY;
            //    y -= movement * Gdx.graphics.getDeltaTime() ;
            }
            if (moveRight && movesH) {
                xv = MAX_VELOCITY;
                faceRight = true;
           //     x += movement * Gdx.graphics.getDeltaTime();
            }
            else if (!moveRight && movesH) {
                xv = MAX_VELOCITY * -1;
                faceRight = false;
            //    x -= movement * Gdx.graphics.getDeltaTime();
            }
        x += xv * Gdx.graphics.getDeltaTime();
        y += yv * Gdx.graphics.getDeltaTime();

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
