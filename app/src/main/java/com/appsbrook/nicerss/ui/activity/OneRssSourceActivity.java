package com.appsbrook.nicerss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
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
import timber.log.Timber;

public class OneRssSourceActivity extends MvpAppCompatActivity
        implements OneRssSourceView {

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

    public static Intent getIntent(final Context context) {
        return new Intent(context, OneRssSourceActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_one_rss_source);
        ButterKnife.bind(this);

        presenter.setAdapterData();
    }

    @Override
    public void setAdapterData(List<String> categories) {

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        rssCategorySpinner.setAdapter(dataAdapter);
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
        Toast.makeText(this, "Failed to save new RSS source! Please, try again!",
                Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.save_rss_source_button)
    public void onSaveRssSourceButtonClick() {

        String rssSourceName = rssSourceNameEditText.getText().toString();
        String rssSourceUrl = rssSourceUrlEditText.getText().toString();
        String rssSourceCategory = (String) rssCategorySpinner.getSelectedItem();
        Timber.d("rssSourceCategory: " + rssSourceCategory);

        presenter.saveRssSource(rssSourceName, rssSourceUrl, rssSourceCategory);
    }
}
