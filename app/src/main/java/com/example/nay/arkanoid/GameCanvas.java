package com.example.nay.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;
import android.view.WindowManager;

import com.example.nay.arkanoid.GameObjects.Ball;
import com.example.nay.arkanoid.GameObjects.Brick;
import com.example.nay.arkanoid.GameObjects.Paddle;

import java.util.ArrayList;

public class GameCanvas extends View {
    private Paint paint;
    private float maxX, maxY;
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
        setMaxSizes(context);

        paddle = new Paddle(maxX/2, maxY*0.9f, maxX*0.1f, 5, Color.WHITE);
        ball = new Ball(maxX/2, maxY*.89f, 3, Color.WHITE);
    }

    private void setMaxSizes(Context context){
        Point size = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(size);
        maxX = size.x;
        maxY = size.y;
    }

    public void update(){

    }

    @Override
    protected void onDraw(Canvas canvas){
        drawBackground(canvas);
        paddle.draw(canvas, paint);
        ball.draw(canvas, paint);
    }

    private void drawBackground(Canvas canvas){
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, maxX, maxY, paint);
    }
}
