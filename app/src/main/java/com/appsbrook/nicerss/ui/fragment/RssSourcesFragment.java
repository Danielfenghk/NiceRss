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
import com.appsbrook.nicerss.events.RssSourceConfirmDeleteEvent;
import com.appsbrook.nicerss.models.RssSource;
import com.appsbrook.nicerss.presentation.presenter.RssSourcesPresenter;
import com.appsbrook.nicerss.presentation.view.RssSourcesView;
import com.appsbrook.nicerss.ui.adapters.RssSourcesAdapter;
import com.appsbrook.nicerss.ui.dialogs.ConfirmDeleteRssSourceDialog;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RssSourcesFragment extends MvpAppCompatFragment
        implements RssSourcesView, RssSourcesAdapter.RssSourcesAdapterHost {

    @InjectPresenter
    RssSourcesPresenter mRssSourcesPresenter;

    @BindView(R.id.rss_sources_recycler_view)
    RecyclerView rssSourcesRecyclerView;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    Unbinder unbinder;
    private RssSourcesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rss_sources, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRssSourcesRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    public static RssSourcesFragment newInstance() {
        return new RssSourcesFragment();
    }

    private void setupRssSourcesRecyclerView() {

        int spanCount = 2;
        rssSourcesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));

        adapter = new RssSourcesAdapter(this);
        rssSourcesRecyclerView.setAdapter(adapter);

        mRssSourcesPresenter.setAdapterData();
    }

    @Override
    public void onRssSourceClick(RssSource rssSource) {

        // TODO open edit rss source dialog
        Toast.makeText(getActivity(), "Click: " + rssSource, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRssSourceLongClick(RssSource rssSource) {

        ConfirmDeleteRssSourceDialog dialog = ConfirmDeleteRssSourceDialog.newInstance(rssSource);
        dialog.show(getActivity().getSupportFragmentManager(), "confirm_delete");
    }

    @Override
    public void setAdapterData(List<RssSource> allRssSources) {

        adapter.updateData(allRssSources);
    }

    public void deleteRssSource(RssSource rssSource) {

        Toast.makeText(getActivity(), "Is deleted...", Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void onRssSourceConfirmDelete(RssSourceConfirmDeleteEvent event) {

        RssSource rssSource = event.getRssSource();

        Toast.makeText(getActivity(), "To delete " + rssSource, Toast.LENGTH_SHORT).show();
    }
}
