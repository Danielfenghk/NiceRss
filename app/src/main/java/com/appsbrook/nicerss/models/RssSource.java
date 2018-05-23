package com.appsbrook.nicerss.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RssSource implements Parcelable {

    @Id
    private long id;

    private String name;
    private String url;
    private ToOne<RssCategory> category;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeSerializable(this.category);
    }

    protected RssSource(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.url = in.readString();
        this.category = (ToOne<RssCategory>) in.readSerializable();
    }

    public static final Parcelable.Creator<RssSource> CREATOR = new Parcelable.Creator<RssSource>() {
        @Override
        public RssSource createFromParcel(Parcel source) {
            return new RssSource(source);
        }

        @Override
        public RssSource[] newArray(int size) {
            return new RssSource[size];
        }
    };
}
