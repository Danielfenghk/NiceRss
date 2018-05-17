package com.appsbrook.nicerss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.presentation.presenter.MainMvpPresenter;
import com.appsbrook.nicerss.presentation.view.MainMvpView;
import com.appsbrook.nicerss.ui.fragment.NewsItemsFragment;
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

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @OnClick(R.id.fab)
    void onFabClicked() {

    }

    public static Intent newIntent(Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        return intent;
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

        if(getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, NewsItemsFragment.newInstance())
                    .commit();
        }
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

            default:
                throw new RuntimeException("");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSettings() {

        Toast.makeText(this, "Settings clicked!", Toast.LENGTH_SHORT).show();
    }
}
