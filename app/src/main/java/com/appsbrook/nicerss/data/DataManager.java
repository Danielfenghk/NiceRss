package com.appsbrook.nicerss.data;

import com.appsbrook.nicerss.models.RssCategory;
import com.appsbrook.nicerss.models.RssCategory_;
import com.appsbrook.nicerss.models.RssItem;
import com.appsbrook.nicerss.models.RssItem_;
import com.appsbrook.nicerss.models.RssSource;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class DataManager {

    private final Box<RssSource> rssSourceBox;
    private final Box<RssCategory> rssCategoryBox;
    private final Box<RssItem> favoritesBox;

    @Inject
    public DataManager(BoxStore boxStore) {
        rssSourceBox = boxStore.boxFor(RssSource.class);
        rssCategoryBox = boxStore.boxFor(RssCategory.class);
        favoritesBox = boxStore.boxFor(RssItem.class);
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

    public void deleteRssSource(long rssSource) {
        rssSourceBox.remove(rssSource);
    }

    public long updateRssSource(RssSource rssSource) {
        return rssSourceBox.put(rssSource);
    }

    public long saveToFavorites(RssItem item) {
        return favoritesBox.put(item);
    }

    public void removeFromFavorites(RssItem item) {
        favoritesBox.remove(item);
    }

    public boolean isRssItemSavedToFavorites(String link) {

        List<RssItem> items = favoritesBox.query().equal(RssItem_.link, link)
                .build().find();

        return items.size() > 0;
    }
}
