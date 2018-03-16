package com.rxandroid.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.rxandroid.R;

/**
 * Created by Devrepublic-14 on 3/13/2018.
 */

public class MaterialActivity extends BaseActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
//  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Toast.makeText(MaterialActivity.this, "click here.", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
