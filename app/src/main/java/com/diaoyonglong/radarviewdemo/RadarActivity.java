package com.diaoyonglong.radarviewdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.diaoyonglong.radarview.RadarView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diaoyonglong on 2019/4/15
 *
 * @desc 雷达扫描
 */
public class RadarActivity extends AppCompatActivity {

    private RadarView radarView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;

    private List<View> showViews = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);

        radarView = findViewById(R.id.radar);
        textView1 = findViewById(R.id.txt_1);
        textView2 = findViewById(R.id.txt_2);
        textView3 = findViewById(R.id.txt_3);
        textView4 = findViewById(R.id.txt_4);
        textView5 = findViewById(R.id.txt_5);
        textView6 = findViewById(R.id.txt_6);

        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);
        textView5.setVisibility(View.INVISIBLE);
        textView6.setVisibility(View.INVISIBLE);

        showViews.add(textView1);
        showViews.add(textView2);
        showViews.add(textView3);
        showViews.add(textView4);
        showViews.add(textView5);
        showViews.add(textView6);

        findViewById(R.id.txt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBarItemAnimation(textView1);
                Toast.makeText(RadarActivity.this, "Text1", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.txt_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBarItemAnimation(textView2);
                Toast.makeText(RadarActivity.this, "Text2", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.txt_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBarItemAnimation(textView3);
                Toast.makeText(RadarActivity.this, "Text3", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.txt_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBarItemAnimation(textView4);
                Toast.makeText(RadarActivity.this, "Text4", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.txt_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBarItemAnimation(textView5);
                Toast.makeText(RadarActivity.this, "Text5", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.txt_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBarItemAnimation(textView6);
                Toast.makeText(RadarActivity.this, "Text6", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radarView.start();
                for (int i = 0; i < showViews.size(); i++) {
                    final View child = showViews.get(i);
                    child.setVisibility(View.INVISIBLE);
                    final int finalI = i;
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            child.setVisibility(View.VISIBLE);
                            setBarItemAnimation((TextView) child);
                            if (finalI == showViews.size() - 1) {
                                radarView.stop();
                            }
                        }
                    }, i * 1100);

                }
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radarView.stop();
            }
        });

    }

    /**
     * 点击动画
     *
     * @param textView
     */
    private void setBarItemAnimation(TextView textView) {

        ValueAnimator scaleX = ObjectAnimator.ofFloat(textView, "scaleX", 1.0f, 0.85f, 1.0f, 1.1f, 1.0f);
        scaleX.setInterpolator(new AccelerateInterpolator());

        ValueAnimator scaleY = ObjectAnimator.ofFloat(textView, "scaleY", 1.0f, 0.85f, 1.0f, 1.1f, 1.0f);
        scaleY.setInterpolator(new AccelerateInterpolator());

        AnimatorSet set = new AnimatorSet();
        set.setDuration(300);
        set.play(scaleX).with(scaleY);
        set.start();
    }

}
