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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                parseRss();

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void parseRss() {

        //url of RSS feed
        String urlString = "http://www.feedforall.com/sample.xml";
        Parser parser = new Parser();
        parser.execute(urlString);
        parser.onFinish(new Parser.OnTaskCompleted() {

            @Override
            public void onTaskCompleted(ArrayList<Article> list) {

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
                }
            }

            @Override
            public void onError() {
                //what to do in case of error
            }
        });
    }

}
