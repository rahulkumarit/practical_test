package com.rxandroid.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rxandroid.R;

/**
 * Created by Devrepublic-14 on 3/14/2018.
 */

public class RecaptchaActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recaptcha);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {

    }

}
