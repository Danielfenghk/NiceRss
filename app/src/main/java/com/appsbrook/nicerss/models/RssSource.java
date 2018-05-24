package com.appsbrook.nicerss.models;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RssSource implements Serializable {

    @Id
    private long id;

    private String name;
    private String url;
    private ToOne<RssCategory> category;
}
