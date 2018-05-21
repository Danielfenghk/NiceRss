package com.appsbrook.nicerss.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.TheApp;
import com.appsbrook.nicerss.data.DataManager;
import com.appsbrook.nicerss.models.RssSource;
import com.appsbrook.nicerss.presentation.presenter.RssSourcesPresenter;
import com.appsbrook.nicerss.presentation.view.RssSourcesView;
import com.appsbrook.nicerss.ui.adapters.RssSourcesAdapter;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RssSourcesFragment extends MvpAppCompatFragment
        implements RssSourcesView, RssSourcesAdapter.RssSourcesAdapterHost {

    @InjectPresenter
    RssSourcesPresenter mRssSourcesPresenter;

    @Inject
    DataManager dataManager;

    @BindView(R.id.rss_sources_recycler_view)
    RecyclerView rssSourcesRecyclerView;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    Unbinder unbinder;

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
        unbinder = ButterKnife.bind(this, view);

        TheApp.getAppComponent().inject(this);

        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int spanCount = 2;

        rssSourcesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));

        List<RssSource> rssSources = dataManager.getAllRssSources();
        RssSourcesAdapter adapter = new RssSourcesAdapter(this, rssSources);
        rssSourcesRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRssSourceClick(RssSource rssSource) {

        Toast.makeText(getActivity(), "Click: " + rssSource, Toast.LENGTH_SHORT).show();
    }
}
