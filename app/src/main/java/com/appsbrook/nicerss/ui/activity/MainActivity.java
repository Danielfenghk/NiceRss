package com.appsbrook.nicerss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.presentation.presenter.MainMvpPresenter;
import com.appsbrook.nicerss.presentation.view.MainMvpView;
import com.appsbrook.nicerss.ui.fragment.RssItemsFragment;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hugo.weaving.DebugLog;

@DebugLog
public class MainActivity extends MvpAppCompatActivity
        implements MainMvpView {

    @InjectPresenter
    MainMvpPresenter presenter;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent newIntent(Context context) {

        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();
        setupFragment();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setupFragment() {

        // TODO use cicerone to add/replace fragments
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, RssItemsFragment.newInstance())
                    .commit();
        }
    }

    @OnClick(R.id.add_new_source_button)
    void onAddNewSourceButtonClick() {

        presenter.processAddNewSourceClick();
    }

    @Override
    public void showAddNewSourceDialog() {

        Intent intent = OneRssSourceActivity.getIntent(this);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                presenter.onSettingsClick();
                break;

            case R.id.action_rss_sources:
                presenter.onRssSourcesClick();
                break;

            default:
                throw new RuntimeException("");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSettings() {

        // TODO implement showSettings()
        Toast.makeText(this, "Settings clicked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openRssSourcesActivity() {

        Intent intent = RssSourcesActivity.getIntent(this);
        startActivity(intent);
    }
}
