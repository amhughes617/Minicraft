package com.theironyard.minicraft;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by alexanderhughes on 2/19/16.
 */
public class Dude {
    TextureRegion down, up, right, stand, left, downF, upF;
    Animation walkX, walkUp, walkDown;
    static final int WIDTH = 100;
    static final int HEIGHT = 100;
    static final float MAX_VELOCITY = 200;
    float x, y, xv, yv;
    boolean faceRight = true;
}
