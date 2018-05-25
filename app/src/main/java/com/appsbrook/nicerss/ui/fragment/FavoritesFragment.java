package com.appsbrook.nicerss.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.models.RssItem;
import com.appsbrook.nicerss.presentation.presenter.FavoritesPresenter;
import com.appsbrook.nicerss.presentation.view.FavoritesView;
import com.appsbrook.nicerss.ui.activity.OneRssItemActivity;
import com.appsbrook.nicerss.ui.adapters.RssItemsAdapter;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hugo.weaving.DebugLog;

@DebugLog
public class FavoritesFragment extends MvpAppCompatFragment
        implements FavoritesView, RssItemsAdapter.HostingComponent {

    @InjectPresenter
    FavoritesPresenter presenter;
    private RssItemsAdapter rssItemsAdapter;
    Unbinder unbinder;

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.no_items_image_view)
    ImageView noItemsImageView;
    @BindView(R.id.empty_linear_layout)
    LinearLayout emptyLinearLayout;
    @BindView(R.id.no_items_text_view)
    TextView noItemsTextView;
    @BindView(R.id.favorites_recycler_view)
    RecyclerView favoritesRecyclerView;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        unbinder = ButterKnife.bind(this, view);

        setupRecyclerView();

        return view;
    }

    private void setupRecyclerView() {

        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rssItemsAdapter = new RssItemsAdapter(this);
        favoritesRecyclerView.setAdapter(rssItemsAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.loadFavorites();
    }

    @Override
    public void loadFavorites(List<RssItem> rssItems) {
        rssItemsAdapter.updateNewsItems(rssItems);
    }

    @Override
    public void onLoadFavoritesFail() {
        Snackbar.make(frameLayout, "Failed to load favorites",
                Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void updateData(List<RssItem> rssItems) {
        rssItemsAdapter.updateNewsItems(rssItems);
    }

    @Override
    public void showNoFavorites() {
        emptyLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoFavorites() {
        emptyLinearLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onRemoveFavoritesSuccess() {
        Snackbar.make(frameLayout, "Favorites are removed!",
                Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onRemoveFavoritesFail() {
        Snackbar.make(frameLayout, "Failed to remove favorites. Please, try again!",
                Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onRssItemClick(RssItem item) {

        Intent intent = OneRssItemActivity.getIntent(getActivity(), item);
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_favorites, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_remove_all_favorites:
                presenter.removeAllFavorites();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
