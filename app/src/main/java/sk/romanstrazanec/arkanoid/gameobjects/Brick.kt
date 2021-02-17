package sk.romanstrazanec.arkanoid.gameobjects

import android.graphics.Canvas
import android.graphics.Paint

class Brick(x: Float, y: Float, private val width: Float, private val height: Float, color: Int) : GameObject(x, y, color) {
    fun x2(): Float {
        return x + width
    }

    fun y2(): Float {
        return y + height
    }

    override fun draw(canvas: Canvas?, paint: Paint) {
        super.draw(canvas, paint)
        canvas!!.drawRect(x, y, x + width, y + height, paint)
    }
}