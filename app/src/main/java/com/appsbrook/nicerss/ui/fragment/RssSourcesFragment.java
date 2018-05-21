package com.appsbrook.nicerss.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.presentation.presenter.RssSourcesPresenter;
import com.appsbrook.nicerss.presentation.view.RssSourcesView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class RssSourcesFragment extends MvpAppCompatFragment implements RssSourcesView {


    @InjectPresenter
    RssSourcesPresenter mRssSourcesPresenter;


    public static RssSourcesFragment newInstance() {
        RssSourcesFragment fragment = new RssSourcesFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rss_sources, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
