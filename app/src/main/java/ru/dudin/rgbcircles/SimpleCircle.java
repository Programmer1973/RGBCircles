package ru.dudin.rgbcircles;

public class SimpleCircle {
    protected int x;
    protected int y;
    protected int radius;
    protected int color;

    public SimpleCircle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public SimpleCircle getBiggerMainCircle() {
        return new SimpleCircle(x, y, radius * 3);
    }

    public boolean isIntersected(SimpleCircle biggerMainCircle) {
        return radius + biggerMainCircle.getRadius() >= Math.sqrt(Math.pow(x - biggerMainCircle.getX(), 2) + Math.pow(y - biggerMainCircle.getY(), 2));
    }
}