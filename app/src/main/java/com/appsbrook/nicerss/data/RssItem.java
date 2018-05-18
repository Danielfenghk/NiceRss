package com.appsbrook.nicerss.data;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RssItem {

    private String title;
    private String description;
    private List<String> categories;
    private String author;
    private Date pubDate;
    private String link;
    private String image;
}
