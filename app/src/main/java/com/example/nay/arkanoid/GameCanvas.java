package com.example.nay.arkanoid;

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

import com.example.nay.arkanoid.GameObjects.Ball;
import com.example.nay.arkanoid.GameObjects.Brick;
import com.example.nay.arkanoid.GameObjects.Paddle;

import java.util.ArrayList;

public class GameCanvas extends View implements SensorEventListener {
    private Paint paint;
    private Point size;
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
        size = getWindowSize(context);

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        paddle = new Paddle(size.x/2, size.y*0.9f, size.x*0.1f, 5, Color.WHITE);
        ball = new Ball(size.x/2, size.y*.89f, 3, Color.WHITE);
    }

    private Point getWindowSize(Context context){
        Point size = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(size);
        return size;
    }

    public void update(){

    }

    @Override
    protected void onDraw(Canvas canvas){
        drawBackground(canvas);
        paddle.draw(canvas, paint);
        ball.draw(canvas, paint);
        invalidate();
    }

    private void drawBackground(Canvas canvas){
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, size.x, size.y, paint);
    }

    public void stopSensor(){
        sensorManager.unregisterListener(this);
    }

    public void startSensor(){
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            if(paddle.x + sensorEvent.values[1]*3.5 > 0 && paddle.getX2() + sensorEvent.values[1]*3.5 < size.x) paddle.x += sensorEvent.values[1]*3.5;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}
}
