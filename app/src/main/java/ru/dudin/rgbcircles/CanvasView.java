package ru.dudin.rgbcircles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class CanvasView extends View implements ICanvasView {

    private static int width;
    private static int height;
    private GameManager mGameManager;
    private Paint mPaint;
    private Canvas mCanvas;
    private Toast toast;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeight(context);
        initPaint();
        initGameManager();
    }

    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
    }

    private void initGameManager() {
        mGameManager = new GameManager(this, width, height);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        mGameManager.onDraw();
    }

    @Override
    public void drawCircle(SimpleCircle simpleCircle) {
        mPaint.setColor(simpleCircle.getColor());
        mCanvas.drawCircle(simpleCircle.getX(), simpleCircle.getY(), simpleCircle.getRadius(), mPaint);
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void showMessage(String text) {
        if(toast != null)
            toast.cancel();

        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            mGameManager.onTouchEvent(x, y);
        }
        invalidate();
        return true;
    }
}