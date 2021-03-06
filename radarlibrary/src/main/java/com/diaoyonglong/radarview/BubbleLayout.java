package com.diaoyonglong.radarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;


import com.diaoyonglong.radarlibrary.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by diaoyonglong on 2019/4/12
 *
 * @desc 上升的气泡
 */
public class BubbleLayout extends View {

    private List<Bubble> bubbles = new ArrayList<Bubble>();
    private Random random = new Random();
    private int width, height;
    private boolean starting = false;
    private boolean isPause = false;

    public BubbleLayout(Context context) {
        super(context);
    }

    public BubbleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BubbleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        isPause = false;
        width = getWidth();
        height = getHeight();
        if (!starting) {
            starting = true;
            new Thread() {
                public void run() {
                    while (true) {
                        if (isPause) {
                            continue;
                        }
                        try {
                            Thread.sleep(random.nextInt(3) * 300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Bubble bubble = new Bubble();
                        int radius = random.nextInt(20);
                        while (radius == 0) {
                            radius = random.nextInt(20);
                        }
                        float speedY = random.nextFloat() * 5;
                        while (speedY < 1) {
                            speedY = random.nextFloat() * 5;
                        }
                        bubble.setRadius(radius);
                        bubble.setSpeedY(speedY);
                        bubble.setX(width / 2);
                        bubble.setY(height);
                        float speedX = random.nextFloat() - 0.5f;
                        while (speedX == 0) {
                            speedX = random.nextFloat() - 0.5f;
                        }
                        bubble.setSpeedX(speedX * 2);
                        bubbles.add(bubble);
                    }
                }
            }.start();
        }
        Paint paint = new Paint();
        // 绘制渐变正方形
        Shader shader = new LinearGradient(0, 0, 0, height, new int[]{
                getResources().getColor(R.color.blue_bright),
                getResources().getColor(R.color.blue_light),
                getResources().getColor(R.color.blue_dark)},
                null, Shader.TileMode.REPEAT);
        paint.setShader(shader);
        canvas.drawRect(0, 0, width, height, paint);
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setAlpha(200);
        List<Bubble> list = new ArrayList<Bubble>(bubbles);
        for (Bubble bubble : list) {
            if (bubble.getY() - bubble.getSpeedY() <= 0) {
                bubbles.remove(bubble);
            } else {
                int i = bubbles.indexOf(bubble);
                if (bubble.getX() + bubble.getSpeedX() <= bubble.getRadius()) {
                    bubble.setX(bubble.getRadius());
                } else if (bubble.getX() + bubble.getSpeedX() >= width
                        - bubble.getRadius()) {
                    bubble.setX(width - bubble.getRadius());
                } else {
                    bubble.setX(bubble.getX() + bubble.getSpeedX());
                }
                bubble.setY(bubble.getY() - bubble.getSpeedY());
                bubbles.set(i, bubble);
                canvas.drawCircle(bubble.getX(), bubble.getY(),
                        bubble.getRadius(), paint);
            }
        }
        invalidate();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        isPause = true;
    }

    private class Bubble {
        /* 气泡半径 */
        private int radius;
        /* 上升速度 */
        private float speedY;
        /* 平移速度 */
        private float speedX;
        /* 气泡x坐标 */
        private float x;
        /* 气泡y坐标 */
        private float y;


        public int getRadius() {
            return radius;
        }


        public void setRadius(int radius) {
            this.radius = radius;
        }


        public float getX() {
            return x;
        }


        public void setX(float x) {
            this.x = x;
        }


        public float getY() {
            return y;
        }


        public void setY(float y) {
            this.y = y;
        }


        public float getSpeedY() {
            return speedY;
        }


        public void setSpeedY(float speedY) {
            this.speedY = speedY;
        }


        public float getSpeedX() {
            return speedX;
        }


        public void setSpeedX(float speedX) {
            this.speedX = speedX;
        }

    }
}
