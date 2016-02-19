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

import java.util.ArrayList;

public class Minicraft extends ApplicationAdapter {
//    SpriteBatch batch;
//    TextureRegion down, up, right, stand, left, downF, upF;
//    Animation walkX, walkUp, walkDown;
//    static final int WIDTH = 100;
//    static final int HEIGHT = 100;
//    static final float MAX_VELOCITY = 200;
//    float x, y, xv, yv, time;
//    boolean faceRight = true;
    SpriteBatch batch;
    float time;
    Player player = new Player();
    Zombie zombie = new Zombie();
    Dude dude = new Dude();
    FitViewport fitViewport;


    @Override
    public void resize(int width, int height) {
        fitViewport.update(width, height);
    }

    @Override
    public void create () {
        fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player.playerCreate();
        zombie.enemyCreate();
        batch = new SpriteBatch();

//        batch = new SpriteBatch();
//
//        Texture tiles = new Texture("tiles.png");
//        TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
//        down = grid[6][0];
//        downF = new TextureRegion(down);
//        downF.flip(true, false);
//        up = grid[6][1];
//        right = grid[6][3];
//        stand = grid[6][2];
//        left = new TextureRegion(right);
//        left.flip(true, false);
//        upF = new TextureRegion(up);
//        upF.flip(true, false);
//        walkX = new Animation(0.1f, right, stand);
//        walkUp = new Animation(0.1f, up, upF);
//        walkDown = new Animation(0.1f, down, downF);


    }

    @Override
    public void render () {
        time += Gdx.graphics.getDeltaTime();

            player.move();
            zombie.move();
            draw(player, zombie);
    }

    void draw(Player player, Zombie zombie) {

        TextureRegion img;
        if (player.yv > 0) {
            img = player.walkUp.getKeyFrame(time, true);
        }
        else if (player.yv < 0) {
            img = player.walkDown.getKeyFrame(time, true);
        }
        else if (player.xv != 0) {
            img = player.walkX.getKeyFrame(time, true);
        }
        else {
            img = player.stand;
        }
        TextureRegion imgZ;
        if (zombie.yv > 0) {
            imgZ = zombie.walkUp.getKeyFrame(time, true);
        }
        else if (zombie.yv < 0) {
            imgZ = zombie.walkDown.getKeyFrame(time, true);
        }
        else if (zombie.xv != 0) {
            imgZ = zombie.walkX.getKeyFrame(time, true);
        }
        else {
            imgZ = zombie.stand;
        }

        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        if (player.faceRight) {
            batch.draw(img, player.x, player.y, player.WIDTH, player.HEIGHT);
        }
        else {
            batch.draw(img, player.WIDTH + player.x, player.y, -1 * player.WIDTH, player.HEIGHT);
        }
        if (zombie.faceRight) {
            batch.draw(imgZ, zombie.x, zombie.y, zombie.WIDTH, zombie.HEIGHT);
        }
        else {
            batch.draw(imgZ, zombie.WIDTH + zombie.x, zombie.y, -1 * zombie.WIDTH, zombie.HEIGHT);
        }

        batch.end();
    }


//    void move() {
//        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            yv = MAX_VELOCITY;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            yv = -1 * MAX_VELOCITY;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            xv = MAX_VELOCITY;
//            faceRight = true;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            xv = MAX_VELOCITY * -1;
//            faceRight = false;
//        }
//        y += yv * Gdx.graphics.getDeltaTime();
//        x += xv * Gdx.graphics.getDeltaTime();
//        // teleports the sprite to opposite side of screen
//        if (y < (0 - HEIGHT)) {
//            y = Gdx.graphics.getHeight();
//        }
//        if (y > Gdx.graphics.getHeight()) {
//            y = 0 - HEIGHT;
//        }
//        if (x < 0 - WIDTH) {
//            x = Gdx.graphics.getWidth();
//        }
//        if (x > Gdx.graphics.getWidth()) {
//            x = 0 - WIDTH;
//        }
//
//
//        yv = decelerate(yv, 0.5f);
//        xv = decelerate(xv, 0.5f);
//    }
}
