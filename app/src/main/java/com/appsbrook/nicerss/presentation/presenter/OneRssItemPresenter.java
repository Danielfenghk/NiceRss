package com.appsbrook.nicerss.presentation.presenter;


import com.appsbrook.nicerss.data.RssItem;
import com.appsbrook.nicerss.presentation.view.OneRssItemView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.Date;

@InjectViewState
public class OneRssItemPresenter extends MvpPresenter<OneRssItemView> {

    private RssItem item;

    public OneRssItemPresenter(RssItem item) {
        this.item = item;

        String title = item.getTitle();
        String description = item.getDescription();
        Date pubDate = item.getPubDate();
        String author = item.getAuthor();
        String image = item.getImage();
        String link = item.getLink();
        String content = item.getContent();

        String date = pubDate.toString();

        getViewState().showItemTitle(title);
        getViewState().showItemDescription(description);

        Whitelist whitelist = new Whitelist();
        whitelist.addTags("span", "div");
        String allowedHtml = Jsoup.clean(content, whitelist);

        getViewState().showItemContent(allowedHtml);

        // TODO format date to look nice
        getViewState().showPublicationDate(date);

        getViewState().showItemAuthor(author);
        getViewState().showItemImage(image);
        getViewState().showItemLink(link);
    }
}
