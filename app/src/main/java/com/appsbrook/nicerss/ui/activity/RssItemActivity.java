package com.appsbrook.nicerss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.data.RssItem;
import com.appsbrook.nicerss.presentation.presenter.RssItemPresenter;
import com.appsbrook.nicerss.presentation.view.RssItemView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;

@DebugLog
public class RssItemActivity extends MvpAppCompatActivity implements RssItemView {

    private static final String EXTRA_RSS_ITEM = "EXTRA_RSS_ITEM";

    @InjectPresenter
    RssItemPresenter mRssItemPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rss_item_title_text_view)
    TextView rssItemTextView;
    @BindView(R.id.rss_item_image_view)
    ImageView rssItemImageView;
    @BindView(R.id.rss_item_description_text_view)
    TextView rssItemDescriptionTextView;
    @BindView(R.id.rss_item_author_text_view)
    TextView rssItemAuthorTextView;
    @BindView(R.id.rss_item_date_text_view)
    TextView rssItemDateTextView;
    @BindView(R.id.rss_item_link_text_view)
    TextView rssItemLinkTextView;

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

    @Override
    public void showItemDescription(String description) {
        rssItemDescriptionTextView.setText(description);
    }

    @Override
    public void showItemAuthor(String author) {
        rssItemAuthorTextView.setText(author);
    }

    @Override
    public void showItemImage(String image) {

        Picasso.with(this).load(image).into(rssItemImageView);
    }

    @Override
    public void showPublicationDate(String pubDate) {
        rssItemDateTextView.setText(pubDate);
    }

    @Override
    public void showItemLink(String link) {
        rssItemLinkTextView.setText(link);
    }
}
