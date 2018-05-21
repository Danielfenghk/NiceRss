package com.appsbrook.nicerss.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RssItem implements Parcelable {

    private String title;
    private String description;
    private String author;
    private Date pubDate;
    private String link;
    private String image;
    private String content;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.author);
        dest.writeLong(this.pubDate != null ? this.pubDate.getTime() : -1);
        dest.writeString(this.link);
        dest.writeString(this.image);
        dest.writeString(this.content);
    }

    protected RssItem(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.author = in.readString();
        long tmpPubDate = in.readLong();
        this.pubDate = tmpPubDate == -1 ? null : new Date(tmpPubDate);
        this.link = in.readString();
        this.image = in.readString();
        this.content = in.readString();
    }

    public static final Creator<RssItem> CREATOR = new Creator<RssItem>() {
        @Override
        public RssItem createFromParcel(Parcel source) {
            return new RssItem(source);
        }

        @Override
        public RssItem[] newArray(int size) {
            return new RssItem[size];
        }
    };
}
