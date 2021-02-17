package sk.romanstrazanec.arkanoid.java;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

import sk.romanstrazanec.arkanoid.java.gameobjects.Ball;
import sk.romanstrazanec.arkanoid.java.gameobjects.Brick;
import sk.romanstrazanec.arkanoid.java.gameobjects.Counter;
import sk.romanstrazanec.arkanoid.java.gameobjects.Paddle;

public class GameCanvas extends View implements SensorEventListener {
    private final Paint paint;
    private final Point size;
    private Counter counter;
    private final Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private final SensorManager sensorManager;
    private final Sensor accelerometer;

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
        size = getWindowSize(context);

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        paddle = new Paddle(size.x / 2f, size.y * .9f, size.x * .1f, 5, Color.WHITE);
        newGame();
    }

    private void newGame() {
        counter = new Counter(5, 5, 50);
        ball = new Ball(size.x / 2f, size.y * .89f, 3, random(-12, 12), random(-12, -8), Color.WHITE);

        bricks = new ArrayList<>();
        int numberOfBricks = (int) random(10, 30);
        for (int i = 0; i < numberOfBricks; i++) bricks.add(newBrick());
    }

    public static float random(float from, float to) {
        return from + (float) Math.random() * (to - from);
    }

    private Point getWindowSize(Context context) {
        Point size = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(size);
        return size;
    }


    // UPDATE ---------------

    public void update() {
        if (Math.random() > .99) bricks.add(newBrick());
        checkHitWall();
        checkHitPaddle();
        checkHitBrick();
        ball.move();
    }

    private Brick newBrick() {
        return new Brick(random(0, size.x - size.x * .07f), random(size.y * .1f, size.y * .5f), size.x * .07f, 5,
                Color.WHITE);
    }

    private void checkHitWall() {
        if (ball.x - ball.r < 0) ball.hitY();
        if (ball.x + ball.r > size.x) ball.hitY();
        if (ball.y - ball.r < 0) ball.hitX();
        if (ball.y + ball.r > size.y) newGame();
    }

    private void checkHitPaddle() {
        boolean y = ball.y + ball.r > paddle.y && ball.y - ball.r < paddle.y2();
        boolean x = ball.x + ball.r > paddle.x && ball.x - ball.r < paddle.x2();
        if (y && x) ball.hitX();
    }

    private void checkHitBrick() {
        boolean withinX, withinY;
        for (Brick brick : bricks) {
            withinX = ball.x + ball.r > brick.x && ball.x - ball.r < brick.x2();
            withinY = ball.y + ball.r > brick.y && ball.y - ball.r < brick.y2();
            if (withinX && withinY) {
                counter.raise();
                ball.hitX();
                bricks.remove(brick);
                break;
            }
        }
    }


    // DRAW -----------------

    @Override
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        paddle.draw(canvas, paint);
        ball.draw(canvas, paint);
        counter.draw(canvas, paint);
        for (Brick brick : bricks) brick.draw(canvas, paint);
        invalidate();
    }

    private void drawBackground(Canvas canvas) {
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, size.x, size.y, paint);
    }


    // SENSOR ---------------

    public void stopSensor() {
        sensorManager.unregisterListener(this);
    }

    public void startSensor() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if (paddle.x + sensorEvent.values[1] * 3.5 > 0 && paddle.x2() + sensorEvent.values[1] * 3.5 < size.x)
                paddle.x += sensorEvent.values[1] * 3.5;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
