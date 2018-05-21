package com.appsbrook.nicerss.data;

import com.appsbrook.nicerss.interactors.IRssItemsLoaderInteractor;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class RssLoader {

    @Inject
    public RssLoader() {
    }

    public void loadRssItems(String url,
                             final IRssItemsLoaderInteractor interactor) {

        //url of RSS feed
        Parser parser = new Parser();
        parser.execute(url);
        parser.onFinish(new Parser.OnTaskCompleted() {

            @Override
            public void onTaskCompleted(ArrayList<Article> list) {

                List<RssItem> items = new ArrayList<>();

                for (Article article : list) {

                    String title = article.getTitle();
                    String author = article.getAuthor();
                    String content = article.getContent();
                    String description = article.getDescription();
                    String image = article.getImage();
                    List<String> categories = article.getCategories();
                    Date pubDate = article.getPubDate();
                    String link = article.getLink();

                    Timber.d("Title: " + title + "\n"
                            + "Author: " + author + "\n"
                            + "Description: " + description + "\n"
                            + "Image: " + image + "\n"
                            + "Categories: " + categories + "\n"
                            + "Publication date: " + pubDate + "\n"
                            + "Link: " + link + "\n"
                            + "Content: " + content + "\n");

                    RssItem item = new RssItem(title, description,
                            author, pubDate, link, image, content);

                    items.add(item);
                }

                interactor.onLoadSuccess(items);
            }

            @Override
            public void onError() {

                interactor.onLoadFail();
            }
        });
    }
}
