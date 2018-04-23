package com.appsbrook.timister.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.appsbrook.timister.R;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity
        implements MainContract.MvpView {

    private MainContract.MvpPresenter presenter;

    @Override
    public void setPresenter(MainContract.MvpPresenter presenter) {
        Timber.d("setPresenter");

        this.presenter = presenter;
    }

    public static Intent newIntent(Context context) {
        Timber.d("newIntent");

        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        setContentView(R.layout.activity_main);

        setPresenter(new MainMvpPresenter(this));
        presenter.start();

        setupToolbar();
        setupFab();
    }

    private void setupToolbar() {
        Timber.d("setupToolbar");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupFab() {
        Timber.d("setupFab");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.processFabClick();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Timber.d("onCreateOptionsMenu");

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Timber.d("onOptionsItemSelected");

        switch (item.getItemId()) {

            case R.id.action_settings:
                Toast.makeText(this, "Settings clicked!", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new RuntimeException("");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showFabSnackBar() {

        Snackbar.make(findViewById(R.id.coordinator_layout),
                "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
