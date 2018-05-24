package com.appsbrook.nicerss.presentation.presenter;


import com.appsbrook.nicerss.interactors.OneRssItemInteractor;
import com.appsbrook.nicerss.models.RssItem;
import com.appsbrook.nicerss.presentation.view.OneRssItemView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.Date;

import timber.log.Timber;

@InjectViewState
public class OneRssItemPresenter extends MvpPresenter<OneRssItemView>
        implements IOneRssItemPresenter {

    private RssItem item;

    private OneRssItemInteractor interactor;

    public OneRssItemPresenter(RssItem item) {
        this.item = item;

        interactor = new OneRssItemInteractor(this);

        displayRssItem();
    }

    private void displayRssItem() {

        displayTitle();
        displayDescription();
        displayContent();
        displayDate();
        displayAuthor();
        displayImage();
        displayLink();

        displayIsAddedToFavorites();
    }

    private void displayIsAddedToFavorites() {

        String link = item.getLink();

        if (interactor.isRssItemSavedToFavorites(link)) {
            getViewState().showRssItemInFavorites();
        } else {
            getViewState().showRssItemNotInFavorites();
        }
    }

    private void displayLink() {
        String link = item.getLink();
        getViewState().showItemLink(link);
    }

    private void displayImage() {
        String image = item.getImage();
        getViewState().showItemImage(image);
    }

    private void displayAuthor() {
        String author = item.getAuthor();
        getViewState().showItemAuthor(author);
    }

    private void displayDate() {
        // TODO format date to look nice
        Date pubDate = item.getPubDate();
        String date = pubDate != null ? pubDate.toString() : "";
        getViewState().showPublicationDate(date);
    }

    private void displayContent() {
        String content = item.getContent();
        String processedContent = processHtml(content);
        getViewState().showItemContent(processedContent);
    }

    private void displayDescription() {
        String description = item.getDescription();
        String processedDescription = processHtml(description);
        getViewState().showItemDescription(processedDescription);
    }

    private void displayTitle() {
        String title = item.getTitle();
        getViewState().showItemTitle(title);
    }

    private String processHtml(String html) {

        html = html != null ? html : "";

        Whitelist whitelist = new Whitelist();
        whitelist.addTags("span");

        return Jsoup.clean(html, whitelist);
    }

    public void onFavoritesButtonClick() {

        String link = item.getLink();

        if (interactor.isRssItemSavedToFavorites(link)) {
            removeFromFavorites();
        } else {
            addToFavorites();
        }
    }

    private void addToFavorites() {
        interactor.addToFavorites(item);
    }

    @Override
    public void onAddToFavoritesSuccess() {
        getViewState().onAddToFavoritesSuccess();
        getViewState().showRssItemInFavorites();
    }

    @Override
    public void onAddToFavoritesFail() {
        getViewState().onAddToFavoritesFailure();
    }

    private void removeFromFavorites() {
        interactor.removeFromFavorites(item);
    }

    @Override
    public void onRemoveFromFavoritesFail() {
        getViewState().onRemoveFromFavoritesFailure();
    }

    @Override
    public void onRemoveFromFavoritesSuccess() {
        getViewState().onRemoveFromFavoritesSuccess();
        getViewState().showRssItemNotInFavorites();
    }
}
