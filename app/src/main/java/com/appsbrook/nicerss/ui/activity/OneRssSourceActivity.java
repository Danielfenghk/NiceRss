package com.appsbrook.nicerss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.appsbrook.nicerss.R;
import com.appsbrook.nicerss.presentation.presenter.OneRssSourcePresenter;
import com.appsbrook.nicerss.presentation.view.OneRssSourceView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hugo.weaving.DebugLog;
import timber.log.Timber;

@DebugLog
public class OneRssSourceActivity extends MvpAppCompatActivity
        implements OneRssSourceView {

    private static final String EXTRA_RSS_SOURCE = "EXTRA_RSS_SOURCE";

    @InjectPresenter
    OneRssSourcePresenter presenter;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.rss_source_name_edit_text)
    MaterialEditText rssSourceNameEditText;
    @BindView(R.id.rss_source_url_edit_text)
    MaterialEditText rssSourceUrlEditText;
    @BindView(R.id.rss_category_spinner)
    Spinner rssCategorySpinner;
    private long toEditId;
    private ArrayAdapter<String> adapter;

    public static Intent getIntent(final Context context) {
        return new Intent(context, OneRssSourceActivity.class);
    }

    public static Intent getIntent(final Context context, long rssSourceId) {

        Intent intent = new Intent(context, OneRssSourceActivity.class);
        intent.putExtra(EXTRA_RSS_SOURCE, rssSourceId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_one_rss_source);
        ButterKnife.bind(this);

        toEditId = getIntent().getLongExtra(EXTRA_RSS_SOURCE, 0);

        Timber.d("toEditId: " + toEditId);

        presenter.setupUi(toEditId);

    }

    @Override
    public void setAdapterData(List<String> categories) {

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        rssCategorySpinner.setAdapter(adapter);
    }

    @Override
    public void promptFillEmptyName() {
        rssSourceNameEditText.setError("Please, enter RSS source name!");
    }

    @Override
    public void promptFillEmptyUrl() {
        rssSourceUrlEditText.setError("Please, enter RSS source URL!");
    }

    @Override
    public void promptEnterCorrectUrl() {
        rssSourceUrlEditText.post(new Runnable() {
            @Override
            public void run() {

                rssSourceUrlEditText.setError("Invalid Rss source Url!");
            }
        });
    }

    @Override
    public void onSaveRssSourceSuccess() {
        Toast.makeText(this, "New RSS source is saved!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onSaveRssSourceFailure() {
        Snackbar.make(coordinatorLayout, "Failed to save new RSS source! Please, try again!",
                Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void setRssSourceName(String name) {
        rssSourceNameEditText.setText(name);
    }

    @Override
    public void setRssSourceUrl(String url) {
        rssSourceUrlEditText.setText(url);
    }

    @Override
    public void setRssSourceCategory(int position) {
        rssCategorySpinner.setSelection(position);
    }

    @Override
    public void onEditRssSourceSuccess() {
        Toast.makeText(this, "Rss sources is successfully updated!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onEditRssSourceFailure() {
        Snackbar.make(coordinatorLayout, "Failed to update new RSS source! Please, try again!",
                Snackbar.LENGTH_SHORT)
                .show();
    }

    @OnClick(R.id.save_rss_source_button)
    public void onSaveRssSourceButtonClick() {

        String rssSourceName = rssSourceNameEditText.getText().toString();
        String rssSourceUrl = rssSourceUrlEditText.getText().toString();
        String rssSourceCategory = (String) rssCategorySpinner.getSelectedItem();

        // TODO implement edit existing
        presenter.attemptSaveRssSource(rssSourceName, rssSourceUrl, rssSourceCategory);
    }
}
