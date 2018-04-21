package com.example.nay.arkanoid.GameObjects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class GameObject {
    public float x, y;
    protected int color;

    public GameObject(float x, float y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(color);
    }
}
