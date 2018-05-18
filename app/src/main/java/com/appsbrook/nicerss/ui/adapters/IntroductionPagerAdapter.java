package com.appsbrook.nicerss.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.appsbrook.nicerss.ui.fragment.IntroductionFragment;

import hugo.weaving.DebugLog;

@DebugLog
public class IntroductionPagerAdapter extends FragmentPagerAdapter {

    public IntroductionPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return IntroductionFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
