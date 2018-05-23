package com.appsbrook.nicerss.data;

import com.appsbrook.nicerss.models.RssCategory;
import com.appsbrook.nicerss.models.RssCategory_;
import com.appsbrook.nicerss.models.RssSource;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class DataManager {

    private final Box<RssSource> rssSourceBox;
    private final Box<RssCategory> rssCategoryBox;

    @Inject
    public DataManager(BoxStore boxStore) {
        rssSourceBox = boxStore.boxFor(RssSource.class);
        rssCategoryBox = boxStore.boxFor(RssCategory.class);
    }

    public long putRssSource(RssSource rssSource) {
        return rssSourceBox.put(rssSource);
    }

    public RssSource getRssSource(long id) {
        return rssSourceBox.get(id);
    }

    public List<RssSource> getAllRssSources() {
        return rssSourceBox.getAll();
    }

    public long putRssCategory(RssCategory rssCategory) {
        return rssCategoryBox.put(rssCategory);
    }

    public RssCategory getRssCategory(long id) {
        return rssCategoryBox.get(id);
    }

    public RssCategory getRssCategoryByTitle(String title) {

        List<RssCategory> rssCategories = rssCategoryBox.query()
                .equal(RssCategory_.title, title)
                .build()
                .find();

        return rssCategories.isEmpty() ? null : rssCategories.get(0);
    }

    public List<RssCategory> getAllCategories() {
        return rssCategoryBox.getAll();
    }

    public void deleteRssSource(RssSource rssSource) {
        rssSourceBox.remove(rssSource);
    }
}
