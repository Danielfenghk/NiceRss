package com.appsbrook.nicerss.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import com.appsbrook.nicerss.models.RssItem;
import com.appsbrook.nicerss.presentation.presenter.FavoritesPresenter;
import com.appsbrook.nicerss.presentation.view.FavoritesView;
import com.appsbrook.nicerss.ui.adapters.RssItemsAdapter;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hugo.weaving.DebugLog;
import timber.log.Timber;

@DebugLog
public class FavoritesFragment extends MvpAppCompatFragment
        implements FavoritesView, RssItemsAdapter.HostingComponent {

    @InjectPresenter
    FavoritesPresenter presenter;

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
    Unbinder unbinder;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onLoadFavoritesFail() {
        Snackbar.make(frameLayout, "Failed to load favorites",
                Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showNoFavorites() {
        emptyLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFavorites(List<RssItem> rssItems) {

        for (RssItem rssItem : rssItems) {
            Timber.d("Favorite: " + rssItem);
        }

        emptyLinearLayout.setVisibility(View.INVISIBLE);

        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RssItemsAdapter rssItemsAdapter = new RssItemsAdapter(rssItems, this);
        favoritesRecyclerView.setAdapter(rssItemsAdapter);

    }

    @Override
    public void onRssItemClick(RssItem item) {

        Snackbar.make(frameLayout, "Click: " + item.getTitle(),
                Snackbar.LENGTH_SHORT)
                .show();
    }
}
