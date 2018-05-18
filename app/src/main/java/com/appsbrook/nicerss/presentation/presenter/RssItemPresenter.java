package com.appsbrook.nicerss.presentation.presenter;


import com.appsbrook.nicerss.data.RssItem;
import com.appsbrook.nicerss.presentation.view.RssItemView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class RssItemPresenter extends MvpPresenter<RssItemView> {

    private RssItem item;

    public RssItemPresenter(RssItem item) {
        this.item = item;

        String title = item.getTitle();
        String author = item.getAuthor();
        String description = item.getDescription();
        String image = item.getImage();
        String link = item.getLink();


        getViewState().showItemTitle(title);
    }
}
