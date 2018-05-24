package com.appsbrook.nicerss.models;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RssItem implements Serializable {

    private String title;
    private String description;
    private String author;
    private Date pubDate;
    private String link;
    private String image;
    private String content;
    private RssSource rssSource;

}
