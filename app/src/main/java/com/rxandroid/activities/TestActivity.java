package com.rxandroid.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.rxandroid.R;

/**
 * Created by Devrepublic-14 on 3/12/2018.
 */

public class TestActivity extends AppCompatActivity {

    private EditText edtFirst, edtSeconds;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        try {
            inits();
        } catch (Exception e) {
        }
    }

    private void inits() {
        Button btnSwap = findViewById(R.id.btnSwap);
        btnSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapNumber();
            }
        });
    }

    private void swapNumber() {
        edtFirst = findViewById(R.id.edtFirst);
        edtSeconds = findViewById(R.id.edtSeconds);
        int a = Integer.parseInt(edtFirst.getText().toString().trim());
        int b = Integer.parseInt(edtSeconds.getText().toString().trim());
        Toast.makeText(this, "Before swap:" + a + "," + b, Toast.LENGTH_SHORT).show();
        //a=10 b=20
        a = a + b;
        b = a - b;
        a = a - b;
        Toast.makeText(this, "After swap:" + a + "," + b, Toast.LENGTH_SHORT).show();
    }

}
