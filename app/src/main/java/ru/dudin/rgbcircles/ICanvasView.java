package ru.dudin.rgbcircles;

public interface ICanvasView {
    void drawCircle(SimpleCircle simpleCircle);

    void redraw();

    void showMessage(String text);
}
