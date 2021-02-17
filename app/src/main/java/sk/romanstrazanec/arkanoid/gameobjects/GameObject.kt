package sk.romanstrazanec.arkanoid.gameobjects

import android.graphics.Canvas
import android.graphics.Paint

open class GameObject(var x: Float, var y: Float, var color: Int) {
    open fun draw(canvas: Canvas?, paint: Paint) {
        paint.color = color
    }
}