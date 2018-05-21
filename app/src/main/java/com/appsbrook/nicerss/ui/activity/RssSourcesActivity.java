package com.appsbrook.nicerss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.ui.fragment.RssSourcesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RssSourcesActivity extends AppCompatActivity {

    public static Intent getIntent(final Context context) {
        return new Intent(context, RssSourcesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_sources);
        ButterKnife.bind(this);

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, RssSourcesFragment.newInstance())
                    .commit();
        }

    }
}
