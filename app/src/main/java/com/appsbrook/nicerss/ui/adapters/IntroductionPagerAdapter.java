package com.appsbrook.nicerss.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.appsbrook.nicerss.ui.fragments.IntroductionFragment;

public class IntroductionPagerAdapter extends FragmentPagerAdapter {

    public IntroductionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return IntroductionFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
