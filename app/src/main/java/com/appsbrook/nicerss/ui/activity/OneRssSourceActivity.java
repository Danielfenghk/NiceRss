package com.appsbrook.nicerss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.Spinner;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.presentation.presenter.OneRssSourcePresenter;
import com.appsbrook.nicerss.presentation.view.OneRssSourceView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OneRssSourceActivity extends MvpAppCompatActivity implements OneRssSourceView {

    @InjectPresenter
    OneRssSourcePresenter mOneRssSourcePresenter;

    @BindView(R.id.rss_source_name_edit_text)
    MaterialEditText rssSourceNameEditText;
    @BindView(R.id.rss_source_url_edit_text)
    MaterialEditText rssSourceUrlEditText;
    @BindView(R.id.rss_category_spinner)
    Spinner rssCategorySpinner;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, OneRssSourceActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_one_rss_source);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.save_rss_source_button)
    public void onViewClicked() {

        Snackbar.make(findViewById(android.R.id.content), "Click!",
                Snackbar.LENGTH_SHORT)
                .show();
    }
}
