package com.example.nay.arkanoid.GameObjects;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.nay.arkanoid.GameCanvas;

public class Ball extends GameObject {
    public float r;
    private float dirX, dirY;

    public Ball(float x, float y, float r, float dirX, float dirY, int color) {
        super(x, y, color);
        this.r = r;
        this.dirX = dirX;
        this.dirY = dirY;
    }

    @Override
    public void draw(Canvas canvas, Paint paint){
        super.draw(canvas, paint);
        canvas.drawCircle(x, y, r, paint);
    }

    public void move(){
        x += dirX;
        y += dirY;
    }

    public void hitX(){
        dirX += GameCanvas.random(-.1f, .1f);
        dirY = -dirY;
    }

    public void hitY(){
        dirX = -dirX;
        dirY += GameCanvas.random(-.1f, .1f);
    }

    public void hitX(float randomRange){
        dirX += GameCanvas.random(-randomRange, randomRange);
        dirY = -dirY;
    }

    public void hitY(float randomRange){
        dirX = -dirX;
        dirY += GameCanvas.random(-randomRange, randomRange);
    }
}
