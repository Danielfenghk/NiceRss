package com.appsbrook.nicerss.presentation.presenter;


import com.appsbrook.nicerss.data.RssItem;
import com.appsbrook.nicerss.presentation.view.RssItemView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Date;

@InjectViewState
public class RssItemPresenter extends MvpPresenter<RssItemView> {

    private RssItem item;

    public RssItemPresenter(RssItem item) {
        this.item = item;

        String title = item.getTitle();
        String description = item.getDescription();
        Date pubDate = item.getPubDate();
        String author = item.getAuthor();
        String image = item.getImage();
        String link = item.getLink();

        String date = pubDate.toString();

        getViewState().showItemTitle(title);
        getViewState().showItemDescription(description);
        getViewState().showPublicationDate(date);
        getViewState().showItemAuthor(author);
        getViewState().showItemImage(image);
        getViewState().showItemLink(link);
    }
}
