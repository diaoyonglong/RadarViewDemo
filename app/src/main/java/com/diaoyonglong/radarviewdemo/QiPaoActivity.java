package com.diaoyonglong.radarviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.diaoyonglong.radarview.BubbleLayout;


/**
 * Created by diaoyonglong on 2019/4/15
 *
 * @desc 上升气泡界面
 */
public class QiPaoActivity extends AppCompatActivity {

    BubbleLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qipao);
        view = findViewById(R.id.view);
    }
}
