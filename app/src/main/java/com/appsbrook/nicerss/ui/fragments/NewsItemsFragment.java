package com.appsbrook.nicerss.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appsbrook.nicerss.R;

public class NewsItemsFragment extends Fragment {


    public NewsItemsFragment() {
        // Required empty public constructor
    }

    public static NewsItemsFragment newInstance() {
        NewsItemsFragment fragment = new NewsItemsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_items, container, false);

        return view;
    }
}
