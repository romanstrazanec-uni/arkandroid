package com.example.nay.arkanoid.GameObjects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Paddle extends GameObject {
    private float width, height;

    public Paddle(float x, float y, float width, float height, int color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas, Paint paint){
        super.draw(canvas, paint);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
}
