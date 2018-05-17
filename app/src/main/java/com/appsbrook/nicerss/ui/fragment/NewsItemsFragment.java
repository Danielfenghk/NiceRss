package com.appsbrook.nicerss.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.presentation.presenter.NewsItemsPresenter;
import com.appsbrook.nicerss.presentation.view.NewsItemsView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsItemsFragment extends MvpAppCompatFragment implements NewsItemsView {

    @InjectPresenter
    NewsItemsPresenter presenter;

    @BindView(R.id.news_items_recycler_view)
    RecyclerView newsItemsRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    public NewsItemsFragment() {
    }

    public static NewsItemsFragment newInstance() {
        NewsItemsFragment fragment = new NewsItemsFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_items, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
