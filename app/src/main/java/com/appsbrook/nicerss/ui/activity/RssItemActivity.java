package com.appsbrook.nicerss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.data.RssItem;
import com.appsbrook.nicerss.presentation.presenter.RssItemPresenter;
import com.appsbrook.nicerss.presentation.view.RssItemView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;

@DebugLog
public class RssItemActivity extends MvpAppCompatActivity implements RssItemView {

    private static final String EXTRA_RSS_ITEM = "EXTRA_RSS_ITEM";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rss_item_text_view)
    TextView rssItemTextView;

    @InjectPresenter
    RssItemPresenter mRssItemPresenter;

    @ProvidePresenter
    RssItemPresenter provideRssItemPresenter() {

        RssItem item = getIntent().getParcelableExtra(EXTRA_RSS_ITEM);
        return new RssItemPresenter(item);
    }

    public static Intent getIntent(final Context context, RssItem item) {
        Intent intent = new Intent(context, RssItemActivity.class);
        intent.putExtra(EXTRA_RSS_ITEM, item);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rss_item);
        ButterKnife.bind(this);

        setupNavigationToParent();
    }

    private void setupNavigationToParent() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showItemTitle(String title) {

        rssItemTextView.setText(title);
    }
}
