package com.example.nay.arkanoid.GameObjects;

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
}
