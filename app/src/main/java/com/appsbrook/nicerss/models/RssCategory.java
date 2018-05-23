package com.appsbrook.nicerss.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import lombok.Data;

@Entity
@Data
public class RssCategory {

    @Id
    private long id;
    private String title;
    private int image;
}
