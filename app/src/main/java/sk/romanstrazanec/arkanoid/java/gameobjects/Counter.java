package sk.romanstrazanec.arkanoid.java.gameobjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Counter extends GameObject {
    private int num;
    private final int textSize;

    public Counter(float x, float y, int textSize) {
        super(x, y + textSize + 2, Color.WHITE);
        this.textSize = textSize;
        num = 0;
    }

    public void raise() {
        num++;
    }

    public void lower() {
        num--;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas, paint);
        paint.setTextSize(textSize);
        canvas.drawText(String.valueOf(num), x, y, paint);
    }
}
