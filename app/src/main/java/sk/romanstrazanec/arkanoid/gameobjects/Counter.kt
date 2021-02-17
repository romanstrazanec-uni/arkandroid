package sk.romanstrazanec.arkanoid.gameobjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Counter(x: Float, y: Float, private val textSize: Int) : GameObject(x, y + textSize + 2, Color.WHITE) {
    private var num = 0
    fun raise() {
        num++
    }

    fun lower() {
        num--
    }

    override fun draw(canvas: Canvas?, paint: Paint) {
        super.draw(canvas, paint)
        paint.textSize = textSize.toFloat()
        canvas!!.drawText(num.toString(), x, y, paint)
    }
}