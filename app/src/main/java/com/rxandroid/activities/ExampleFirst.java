package com.rxandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.rxandroid.R;
import com.rxandroid.servcies.TestService;

/**
 * Created by Devrepublic-14 on 3/9/2018.
 */

public class ExampleFirst extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "RxExample";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            init();
        } catch (Exception e) {
        }
    }

    private void init() {
        Button btnStart = findViewById(R.id.btnStart);
        Button btnStop = findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                startService(new Intent(this, TestService.class));
                break;
            case R.id.btnStop:
                stopService(new Intent(this, TestService.class));
                break;
        }
    }

}
