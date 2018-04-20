package com.example.nay.arkanoid.GameObjects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball extends GameObject {
    private float r;

    public Ball(float x, float y, float r, int color) {
        super(x, y, color);
        this.r = r;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    @Override
    public void draw(Canvas canvas, Paint paint){
        super.draw(canvas, paint);
        canvas.drawCircle(x, y, r, paint);
    }
}
