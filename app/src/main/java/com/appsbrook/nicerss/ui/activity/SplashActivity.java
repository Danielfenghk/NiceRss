package com.appsbrook.nicerss.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hugo.weaving.DebugLog;

@DebugLog
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = IntroductionActivity.newIntent(this);
        startActivity(intent);
        finish();
    }
}
