package sk.romanstrazanec.arkanoid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.View
import android.view.WindowManager
import sk.romanstrazanec.arkanoid.gameobjects.Ball
import sk.romanstrazanec.arkanoid.gameobjects.Brick
import sk.romanstrazanec.arkanoid.gameobjects.Counter
import sk.romanstrazanec.arkanoid.gameobjects.Paddle
import java.util.*

class GameCanvas(context: Context) : View(context), SensorEventListener {
    private val paint: Paint = Paint()
    private val size: Point
    private var counter: Counter? = null
    private val paddle: Paddle
    private var ball: Ball? = null
    private var bricks: ArrayList<Brick>? = null
    private val sensorManager: SensorManager
    private val accelerometer: Sensor

    private fun newGame() {
        counter = Counter(5f, 5f, 50)
        ball = Ball((size.x / 2).toFloat(), size.y * .89f, 3f, random(-12f, 12f), random(-12f, -8f), Color.WHITE)
        bricks = ArrayList()
        val numberOfBricks = random(10f, 30f).toInt()
        for (i in 0 until numberOfBricks) bricks!!.add(newBrick())
    }

    private fun getWindowSize(context: Context): Point {
        val size = Point()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(size)
        return size
    }

    // UPDATE ---------------
    fun update() {
        if (Math.random() > .99) bricks!!.add(newBrick())
        checkHitWall()
        checkHitPaddle()
        checkHitBrick()
        ball!!.move()
    }

    private fun newBrick(): Brick {
        return Brick(random(0f, size.x - size.x * .07f), random(size.y * .1f, size.y * .5f), size.x * .07f, 5f,
                Color.WHITE)
    }

    private fun checkHitWall() {
        if (ball!!.x - ball!!.r < 0) ball!!.hitY()
        if (ball!!.x + ball!!.r > size.x) ball!!.hitY()
        if (ball!!.y - ball!!.r < 0) ball!!.hitX()
        if (ball!!.y + ball!!.r > size.y) newGame()
    }

    private fun checkHitPaddle() {
        val y = ball!!.y + ball!!.r > paddle.y && ball!!.y - ball!!.r < paddle.y2()
        val x = ball!!.x + ball!!.r > paddle.x && ball!!.x - ball!!.r < paddle.x2()
        if (y && x) ball!!.hitX()
    }

    private fun checkHitBrick() {
        var withinX: Boolean
        var withinY: Boolean
        for (brick in bricks!!) {
            withinX = ball!!.x + ball!!.r > brick.x && ball!!.x - ball!!.r < brick.x2()
            withinY = ball!!.y + ball!!.r > brick.y && ball!!.y - ball!!.r < brick.y2()
            if (withinX && withinY) {
                counter!!.raise()
                ball!!.hitX()
                bricks!!.remove(brick)
                break
            }
        }
    }

    // DRAW -----------------
    override fun onDraw(canvas: Canvas) {
        drawBackground(canvas)
        paddle.draw(canvas, paint)
        ball!!.draw(canvas, paint)
        counter!!.draw(canvas, paint)
        for (brick in bricks!!) brick.draw(canvas, paint)
        invalidate()
    }

    private fun drawBackground(canvas: Canvas) {
        paint.color = Color.BLACK
        canvas.drawRect(0f, 0f, size.x.toFloat(), size.y.toFloat(), paint)
    }

    // SENSOR ---------------
    fun stopSensor() {
        sensorManager.unregisterListener(this)
    }

    fun startSensor() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        if (sensorEvent.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            if (paddle.x + sensorEvent.values[1] * 3.5 > 0 && paddle.x2() + sensorEvent.values[1] * 3.5 < size.x) paddle.x += (sensorEvent.values[1] * 3.5).toFloat()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {}

    companion object {
        fun random(from: Float, to: Float): Float {
            return from + Math.random().toFloat() * (to - from)
        }
    }

    init {
        size = getWindowSize(context)
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        paddle = Paddle((size.x / 2).toFloat(), size.y * .9f, size.x * .1f, 5f, Color.WHITE)
        newGame()
    }
}