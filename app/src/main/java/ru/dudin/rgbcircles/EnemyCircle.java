package ru.dudin.rgbcircles;

import android.graphics.Color;

import java.util.Random;

public class EnemyCircle extends SimpleCircle {

    public static final int FROM_RADIUS = 10;
    public static final int TO_RADIUS = 110;
    public static final int FOOD_COLOR = Color.GREEN;
    public static final int ENEMY_COLOR = Color.RED;
    public static final int RANDOM_SPEED = 10;
    private int dx;
    private int dy;


    public EnemyCircle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public static EnemyCircle getEnemyCircle() {
        int x = new Random().nextInt(GameManager.getWidth());
        int y = new Random().nextInt(GameManager.getHeight());
        int radius = FROM_RADIUS + new Random().nextInt(TO_RADIUS - FROM_RADIUS);
        int dx = 1 + new Random().nextInt(RANDOM_SPEED);
        int dy = 1 + new Random().nextInt(RANDOM_SPEED);
        EnemyCircle enemyCircle = new EnemyCircle(x, y, radius, dx, dy);
        return enemyCircle;
    }

    public void setFoodOrEnemyColorDependsOfSize(MainCircle mMainCircle) {
        if(isEnemySmaller(mMainCircle))
            setColor(FOOD_COLOR);
        else
            setColor(ENEMY_COLOR);
    }

    public boolean isEnemySmaller(MainCircle mainCircle) {

        if(getRadius() < mainCircle.getRadius())
            return true;

        return false;
    }

    public void moveEnCircle() {
        x += dx;
        y += dy;
        checkBounds();
    }

    private void checkBounds() {
        if (x > GameManager.getWidth() || x < 0)
            dx = -dx;
        if (y > GameManager.getHeight() || y < 0)
            dy = -dy;
    }

}