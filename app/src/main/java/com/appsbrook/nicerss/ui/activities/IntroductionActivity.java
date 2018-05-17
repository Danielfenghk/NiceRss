package com.appsbrook.nicerss.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.TheApp;
import com.appsbrook.nicerss.data.SettingsManager;
import com.appsbrook.nicerss.ui.adapters.IntroductionPagerAdapter;
import com.appsbrook.nicerss.utils.FadePageTransformerUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroductionActivity extends AppCompatActivity
        implements ViewPager.OnPageChangeListener {

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.next_image_button)
    ImageButton nextImageButton;

    @Inject
    SettingsManager settingsManager;

    private IntroductionPagerAdapter adapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, IntroductionActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TheApp.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_introduction);
        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        runOnce();

        adapter = new IntroductionPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(false, new FadePageTransformerUtil());
        viewPager.addOnPageChangeListener(this);
    }

    private void runOnce() {

        boolean firstLaunch = settingsManager.isFirstLaunch();
        if (!firstLaunch) {
            openMainActivity();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                nextImageButton.setImageResource(R.drawable.ic_arrow_forward);
                break;
            case 1:
                nextImageButton.setImageResource(R.drawable.ic_arrow_forward);
                break;
            case 2:
                nextImageButton.setImageResource(R.drawable.ic_done);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.next_image_button)
    public void onViewClicked() {

        if (viewPager.getCurrentItem() == 2) {

            settingsManager.setFirstLaunch(false);
            openMainActivity();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        }
    }

    private void openMainActivity() {
        Intent intent = MainActivity.newIntent(this);
        startActivity(intent);
        finish();
    }
}
