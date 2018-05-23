package com.appsbrook.nicerss.models;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RssCategory implements Serializable{

    @Id
    private long id;
    private String title;
    private int image;
}
