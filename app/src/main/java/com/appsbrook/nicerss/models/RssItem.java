package com.appsbrook.nicerss.models;

import java.io.Serializable;
import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RssItem implements Serializable {

    @Id
    private long id;

    private String title;
    private String description;
    private String author;
    private Date pubDate;
    private String link;
    private String image;
    private String content;

    private ToOne<RssSource> rssSource;

}
