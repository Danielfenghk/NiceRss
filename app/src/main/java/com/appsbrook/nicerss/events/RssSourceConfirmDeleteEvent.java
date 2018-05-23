package com.appsbrook.nicerss.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RssSourceConfirmDeleteEvent {

    private long rssSourceId;
}
