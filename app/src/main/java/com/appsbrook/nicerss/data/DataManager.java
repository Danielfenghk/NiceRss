package com.appsbrook.nicerss.data;

import com.appsbrook.nicerss.models.RssSource;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class DataManager {

    private final Box<RssSource> rssSourceBox;

    @Inject
    public DataManager(BoxStore boxStore) {
        rssSourceBox = boxStore.boxFor(RssSource.class);
    }

    public long putRssSource(RssSource rssSource) {
        return rssSourceBox.put(rssSource);
    }

    public RssSource getRssSource(long id) {
        return rssSourceBox.get(id);
    }
}
