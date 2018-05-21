package com.appsbrook.nicerss.presentation.view;

import com.arellomobile.mvp.MvpView;

public interface RssItemView extends MvpView {

    void showItemTitle(String title);

    void showItemDescription(String description);

    void showItemAuthor(String author);

    void showItemImage(String image);

    void showPublicationDate(String pubDate);

    void showItemLink(String link);
}
