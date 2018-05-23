package com.appsbrook.nicerss.events;

import com.appsbrook.nicerss.models.RssSource;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RssSourceConfirmDeleteEvent {

    private RssSource rssSource;
}
