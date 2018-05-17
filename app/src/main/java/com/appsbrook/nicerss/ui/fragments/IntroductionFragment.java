package com.appsbrook.nicerss.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class IntroductionFragment extends Fragment {

    private static final String ARG_PAGE_NUMBER = "ARG_PAGE_NUMBER";


    public static Fragment newInstance(int position) {

        IntroductionFragment fragment = new IntroductionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }


}
