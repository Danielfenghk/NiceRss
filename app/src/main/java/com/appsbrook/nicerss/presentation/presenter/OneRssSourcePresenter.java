package com.appsbrook.nicerss.presentation.presenter;

import android.text.TextUtils;

import com.appsbrook.nicerss.TheApp;
import com.appsbrook.nicerss.data.DataManager;
import com.appsbrook.nicerss.models.RssCategory;
import com.appsbrook.nicerss.models.RssSource;
import com.appsbrook.nicerss.presentation.view.OneRssSourceView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class OneRssSourcePresenter extends MvpPresenter<OneRssSourceView> {

    @Inject
    DataManager dataManager;

    private long toEditId;

    public OneRssSourcePresenter() {
        TheApp.getAppComponent().inject(this);
    }

    public void setupUi(long toEditId) {

        List<RssCategory> categories = dataManager.getAllCategories();

        List<String> titles = new ArrayList<>();
        for (RssCategory category : categories) {
            titles.add(category.getTitle());
        }
        getViewState().setAdapterData(titles);

        this.toEditId = toEditId;

        if (toEditId > 0) {

            RssSource rssSource = dataManager.getRssSource(toEditId);
            String name = rssSource.getName();
            String url = rssSource.getUrl();
            RssCategory rssCategory = rssSource.getCategory().getTarget();
            String rssCategoryTitle = rssCategory.getTitle();
            int position = titles.indexOf(rssCategoryTitle);

            getViewState().setRssSourceName(name);
            getViewState().setRssSourceUrl(url);
            getViewState().setRssSourceCategory(position);
        }
    }

    public void attemptSaveRssSource(final String name, final String url,
                                     final String categoryTitle) {

        // TODO check internet connection (cannot validate url without it)

        if (TextUtils.isEmpty(name))
            getViewState().promptFillEmptyName();

        if (TextUtils.isEmpty(url))
            getViewState().promptFillEmptyUrl();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(url)) {
            validateUrlAndSave(name, url, categoryTitle);
        }
    }

    private void validateUrlAndSave(final String name, final String url, final String categoryTitle) {

        if (!isPrefixOk(url)) {
            getViewState().promptEnterCorrectUrl();
            return;
        }

        Parser parser = new Parser();
        parser.execute(url);
        parser.onFinish(new Parser.OnTaskCompleted() {

            @Override
            public void onTaskCompleted(ArrayList<Article> list) {

                storeRssSource(name, url.toLowerCase(), categoryTitle);
            }

            @Override
            public void onError() {
                getViewState().promptEnterCorrectUrl();
            }
        });
    }

    private boolean isPrefixOk(String url) {

        String[] prefixes = {"http://", "https://"};

        for (String prefix : prefixes) {
            if (url.toLowerCase().startsWith(prefix) && !url.toLowerCase().equals(prefix)) {
                return true;
            }
        }
        return false;
    }

    private void storeRssSource(String name, String url, String categoryTitle) {

        if (toEditId > 0) {
            editRssSource(name, url, categoryTitle);
        } else {
            addRssSource(name, url, categoryTitle);
        }
    }

    private void editRssSource(String name, String url, String categoryTitle) {

        RssSource rssSource = dataManager.getRssSource(toEditId);

        rssSource.setName(name);
        rssSource.setUrl(url);
        RssCategory category = dataManager.getRssCategoryByTitle(categoryTitle);
        rssSource.getCategory().setTarget(category);

        long id = dataManager.updateRssSource(rssSource);
        if (id > 0) {
            getViewState().onEditRssSourceSuccess();
        } else {
            getViewState().onEditRssSourceFailure();
        }
    }

    private void addRssSource(String name, String url, String categoryTitle) {

        RssSource rssSource = new RssSource();
        rssSource.setName(name);
        rssSource.setUrl(url);

        RssCategory category = dataManager.getRssCategoryByTitle(categoryTitle);
        rssSource.getCategory().setTarget(category);

        long id = dataManager.putRssSource(rssSource);
        if (id > 0) {
            getViewState().onSaveRssSourceSuccess();
        } else {
            getViewState().onSaveRssSourceFailure();
        }
    }

    public RssSource getRssSource(long toEditId) {
        return dataManager.getRssSource(toEditId);
    }
}
