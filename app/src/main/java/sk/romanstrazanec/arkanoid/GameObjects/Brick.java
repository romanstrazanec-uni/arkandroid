package sk.romanstrazanec.arkanoid.GameObjects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Brick extends GameObject {
    private float width, height;

    public Brick(float x, float y, float width, float height, int color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public float x2(){
        return x + width;
    }

    public float y2(){ return y + height; }

    @Override
    public void draw(Canvas canvas, Paint paint){
        super.draw(canvas, paint);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
}
