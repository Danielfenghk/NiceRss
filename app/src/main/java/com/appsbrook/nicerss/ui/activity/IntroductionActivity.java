package com.appsbrook.nicerss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.ui.adapters.IntroductionPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroductionActivity extends AppCompatActivity
        implements ViewPager.OnPageChangeListener {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.next_image_button)
    ImageButton nextImageButton;

    public static Intent newIntent(Context context) {
        return new Intent(context, IntroductionActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_introduction);
        ButterKnife.bind(this);

        //run once

        new IntroductionPagerAdapter(getSupportFragmentManager());


    }

    @OnClick(R.id.next_image_button)
    public void onViewClicked() {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // TODO
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
