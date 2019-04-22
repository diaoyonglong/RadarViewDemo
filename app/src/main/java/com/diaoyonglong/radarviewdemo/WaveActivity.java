package com.diaoyonglong.radarviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.diaoyonglong.radarview.MyWaveView;


/**
 * Created by diaoyonglong on 2019/4/15
 *
 * @desc 波浪线界面
 */

public class WaveActivity extends AppCompatActivity {

    MyWaveView myView1;
    MyWaveView myView2;
    MyWaveView myView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        myView1 = findViewById(R.id.myView1);
        myView2 = findViewById(R.id.myView2);
        myView3 = findViewById(R.id.myView3);

        myView1.setxoffset(100);
        myView2.setxoffset(200);
        myView3.setxoffset(300);

        myView1.setWaveHeight(60);
        myView2.setWaveHeight(100);
        myView3.setWaveHeight(80);

        myView1.setWaveSpeed(10);
        myView2.setWaveSpeed(20);
        myView3.setWaveSpeed(30);
    }
}
