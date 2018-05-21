package com.appsbrook.nicerss.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import lombok.Data;

@Entity
@Data
public class RssSource {

    @Id
    private long id;

    private String name;
    private String url;
    private String category;


}
