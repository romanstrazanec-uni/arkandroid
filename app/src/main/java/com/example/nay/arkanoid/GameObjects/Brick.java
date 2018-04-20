package com.example.nay.arkanoid.GameObjects;

public class Brick extends GameObject {
    private float width, height;

    public Brick(float x, float y, float width, float height, int color) {
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
}
