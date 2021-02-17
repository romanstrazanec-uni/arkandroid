package sk.romanstrazanec.arkanoid.gameobjects

import android.graphics.Canvas
import android.graphics.Paint
import sk.romanstrazanec.arkanoid.GameCanvas

class Ball(x: Float, y: Float, var r: Float, private var dirX: Float, private var dirY: Float, color: Int) : GameObject(x, y, color) {
    override fun draw(canvas: Canvas?, paint: Paint) {
        super.draw(canvas, paint)
        canvas!!.drawCircle(x, y, r, paint)
    }

    fun move() {
        x += dirX
        y += dirY
    }

    fun hitX() {
        dirX += GameCanvas.random(-.1f, .1f)
        dirY = -dirY
    }

    fun hitY() {
        dirX = -dirX
        dirY += GameCanvas.random(-.1f, .1f)
    }

    fun hitX(randomRange: Float) {
        dirX += GameCanvas.random(-randomRange, randomRange)
        dirY = -dirY
    }

    fun hitY(randomRange: Float) {
        dirX = -dirX
        dirY += GameCanvas.random(-randomRange, randomRange)
    }
}