package sk.romanstrazanec.arkanoid.java.gameobjects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Paddle extends GameObject {
    private final float width, height;

    public Paddle(float x, float y, float width, float height, int color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public float x2() {
        return x + width;
    }

    public float y2() {
        return y + height;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas, paint);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public void move(float speed) {
        this.x += speed;
    }
}
