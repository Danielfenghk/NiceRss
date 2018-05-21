package com.appsbrook.nicerss.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.appsbrook.nicerss.R;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String url = "http://www.feedforall.com/sample.xml";
        final String url2 = "http://feeds.arstechnica.com/arstechnica/index?format=xml";
        String url3 = "https://www.economist.com/sections/economics/rss.xml";

        String name = "Anton Zaviyalov";

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                parseRss(url2);


                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void parseRss(String urlString) {

        //url of RSS feed
        Parser parser = new Parser();
        parser.execute(urlString);
        parser.onFinish(new Parser.OnTaskCompleted() {

            @Override
            public void onTaskCompleted(ArrayList<Article> list) {

                for (Article article : list) {

                    String title = article.getTitle();
                    String description = article.getDescription();
                    String link = article.getLink();
                    Date pubDate = article.getPubDate();
                    String author = article.getAuthor();
                    List<String> categories = article.getCategories();
                    String image = article.getImage();
                    String content = article.getContent();

                    Timber.d("Title: " + title + "\n"
                            + "Author: " + author + "\n"
                            + "Description: " + description + "\n"
                            + "Image: " + image + "\n"
                            + "Categories: " + categories + "\n"
                            + "Publication date: " + pubDate + "\n"
                            + "Link: " + link + "\n"
                            + "Content: " + content + "\n"
                    );
                }
            }

            @Override
            public void onError() {
                //what to do in case of error
            }
        });
    }

}
