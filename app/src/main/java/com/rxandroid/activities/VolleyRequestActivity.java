package com.rxandroid.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.rxandroid.R;

/**
 * Created by SONI on 3/17/2018.
 */

public class VolleyRequestActivity extends BaseActivity {

    private Button btnCheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        btnCheck = findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testVolleyCall();
            }
        });
    }

    private void testVolleyCall() {
        Toast.makeText(this,"click here",Toast.LENGTH_SHORT).show();
        }

 }
