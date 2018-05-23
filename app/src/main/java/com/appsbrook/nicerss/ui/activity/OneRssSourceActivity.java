package com.appsbrook.nicerss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.appsbrook.nicerss.presentation.view.OneRssSourceView;
import com.appsbrook.nicerss.presentation.presenter.OneRssSourcePresenter;

import com.arellomobile.mvp.MvpAppCompatActivity;

import com.appsbrook.nicerss.R;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class OneRssSourceActivity extends MvpAppCompatActivity implements OneRssSourceView {
    public static final String TAG = "OneRssSourceActivity";
    @InjectPresenter
    OneRssSourcePresenter mOneRssSourcePresenter;


    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, OneRssSourceActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_rss_source);
    }
}
