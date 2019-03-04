package ru.dudin.rgbcircles;

import java.util.ArrayList;

public class GameManager {

    private static final int MAX_ENEMY_CIRCLES = 10;
    private static int width;
    private static int heigth;
    private CanvasView mCanvasView;
    private MainCircle mMainCircle;
    private ArrayList<EnemyCircle> mEnemyCircles;

    public GameManager(CanvasView canvasView, int width, int height) {
        this.mCanvasView = canvasView;
        this.width = width;
        this.heigth = height;
        initMainCircle();
        initEnemyCircle();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return heigth;
    }

    private void initMainCircle() {
        mMainCircle = new MainCircle(width/2, heigth/2);
    }

    private void initEnemyCircle() {
        SimpleCircle biggerMainCircle = mMainCircle.getBiggerMainCircle();
        mEnemyCircles = new ArrayList<>();
        for(int i = 0; i < MAX_ENEMY_CIRCLES; i++) {
            EnemyCircle enemyCircle;
            do {
                enemyCircle = EnemyCircle.getEnemyCircle();
            } while (enemyCircle.isIntersected(biggerMainCircle));
            mEnemyCircles.add(enemyCircle);
        }
        calculateAndSetCirclesColor();
    }

    private void calculateAndSetCirclesColor() {
        for(EnemyCircle enemyCircle : mEnemyCircles) {
            enemyCircle.setFoodOrEnemyColorDependsOfSize(mMainCircle);
        }
    }

    public void onDraw() {
        mCanvasView.drawCircle(mMainCircle);
        for(EnemyCircle ec : mEnemyCircles)
            mCanvasView.drawCircle(ec);
    }

    public void onTouchEvent(int x, int y) {
        mMainCircle.moveMainCircleWhenTouchAt(x, y);
        moveEnemyCircle();
        checkCollision();
    }

    private void checkCollision() {
        EnemyCircle removeCircle = null;
        for(EnemyCircle ec : mEnemyCircles) {
            if(mMainCircle.isIntersected(ec)) {
                if(ec.isEnemySmaller(mMainCircle)) {
                    removeCircle = ec;
                    mMainCircle.growRadius(removeCircle);
                    mEnemyCircles.remove(removeCircle);
                    calculateAndSetCirclesColor();
                    break;
                }
                else {
                    gameEnd("YOU LOSE!");
                    return;
                }
            }
        }

//        if(removeCircle != null)
//            mEnemyCircles.remove(removeCircle);

        if(mEnemyCircles.isEmpty())
            gameEnd("YOU WON!");
    }

    private void gameEnd(String text) {
        mCanvasView.showMessage(text);
        mMainCircle.initRadius();
        initEnemyCircle();
        mCanvasView.redraw();
    }

    private void moveEnemyCircle() {
        for(EnemyCircle enemyCircle : mEnemyCircles) {
            enemyCircle.moveEnCircle();
        }
    }
}