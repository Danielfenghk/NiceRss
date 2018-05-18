package com.appsbrook.nicerss.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.data.RssItem;
import com.appsbrook.nicerss.presentation.presenter.NewsItemsPresenter;
import com.appsbrook.nicerss.presentation.view.NewsItemsView;
import com.appsbrook.nicerss.ui.adapters.NewsItemsAdapter;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsItemsFragment extends MvpAppCompatFragment
        implements NewsItemsView, SwipeRefreshLayout.OnRefreshListener {

    @InjectPresenter
    NewsItemsPresenter presenter;

    private NewsItemsAdapter adapter;
    Unbinder unbinder;

    @BindView(R.id.no_items_linear_layout)
    LinearLayout noItemsLinearLayout;
    @BindView(R.id.refresh_image_view)
    ImageView refreshImageView;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.no_items_text_view)
    TextView noItemsTextView;
    @BindView(R.id.news_items_recycler_view)
    RecyclerView newsItemsRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    public NewsItemsFragment() {
    }

    public static NewsItemsFragment newInstance() {

        return new NewsItemsFragment();
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

        swipeRefreshLayout.setOnRefreshListener(this);

        newsItemsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NewsItemsAdapter();
        newsItemsRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @Override
    public void onRefresh() {

        String url = "https://www.technologyreview.com/c/computing/rss/";

        presenter.loadNewsItems(url);
    }

    @Override
    public void updateNewsItems(List<RssItem> items) {

        adapter.updateNewsItems(items);

    }

    @Override
    public void hideNoItemsDisplay() {

        noItemsLinearLayout.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNoItemsDisplay() {

        noItemsLinearLayout.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void reportLoadFailed(String message) {

        Snackbar.make(frameLayout, message, Snackbar.LENGTH_SHORT).show();

    }
}
