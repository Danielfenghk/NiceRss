package com.appsbrook.nicerss.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.presentation.presenter.FavoritesPresenter;
import com.appsbrook.nicerss.presentation.view.FavoritesView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class FavoritesFragment extends MvpAppCompatFragment implements FavoritesView {

    @InjectPresenter
    FavoritesPresenter presenter;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
