package com.appsbrook.nicerss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.presentation.presenter.RssSourcesPresenter;
import com.appsbrook.nicerss.presentation.view.RssSourcesView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class RssSourcesActivity extends MvpAppCompatActivity implements RssSourcesView {

    @InjectPresenter
    RssSourcesPresenter mRssSourcesPresenter;


    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, RssSourcesActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_sources);
    }
}
