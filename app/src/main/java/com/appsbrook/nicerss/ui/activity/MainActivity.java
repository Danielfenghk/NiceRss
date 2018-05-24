package com.appsbrook.nicerss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.presentation.presenter.MainMvpPresenter;
import com.appsbrook.nicerss.presentation.view.MainMvpView;
import com.appsbrook.nicerss.ui.fragment.FavoritesFragment;
import com.appsbrook.nicerss.ui.fragment.RssItemsFragment;
import com.appsbrook.nicerss.ui.fragment.RssSourcesFragment;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hugo.weaving.DebugLog;

@DebugLog
public class MainActivity extends MvpAppCompatActivity
        implements MainMvpView, NavigationView.OnNavigationItemSelectedListener {

    @InjectPresenter
    MainMvpPresenter presenter;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
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
        setupNavigation();
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

    private void setupNavigation() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.rss_feeds_item:
                presenter.openRssItems();
                break;
            case R.id.favorites_item:
                presenter.openFavorites();
                break;
            case R.id.rss_sources_item:
                presenter.openRssSources();
                break;
            case R.id.navigation_settings_item:
                presenter.openSettings();
                break;
            case R.id.navigation_about_item:
                presenter.openAbout();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void openRssItemsFragment() {
        preventReopening(RssItemsFragment.class, RssItemsFragment.newInstance());
    }

    @Override
    public void openRssSourcesFragment() {
        preventReopening(RssSourcesFragment.class, RssSourcesFragment.newInstance());
    }

    @Override
    public void openFavoritesFragment() {
        preventReopening(FavoritesFragment.class, FavoritesFragment.newInstance());
    }

    private void preventReopening(Class<?> cls, Fragment fragment) {

        Fragment openedFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (cls.isInstance(openedFragment)) return;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void openSettingsActivity() {

        // TODO implement openSettingsActivity()
        Toast.makeText(this, "Settings!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openAboutActivity() {

        // TODO
        Toast.makeText(this, "About!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.add_new_source_button)
    void onAddNewSourceButtonClick() {
        presenter.processAddNewSourceClick();
    }

    @Override
    public void openAddNewSourceDialog() {
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

        // TODO replace by actual menu items
        switch (item.getItemId()) {

            case R.id.action_settings:
                presenter.openSettings();
                break;

            case R.id.action_rss_sources:
                presenter.openRssSources();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
