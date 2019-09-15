package com.example.Activity.Bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ContentTableBean implements Parcelable {

    public static final Creator<ContentTableBean> CREATOR = new Creator<ContentTableBean>() {
        @Override
        public ContentTableBean createFromParcel(Parcel source) {
            return new ContentTableBean(source);
        }

        @Override
        public ContentTableBean[] newArray(int size) {
            return new ContentTableBean[size];
        }
    };
    private String id;
    private String name;
    private String content;

    public ContentTableBean() {
    }

    public ContentTableBean(Parcel source) {
        id = source.readString();
        name = source.readString();
        content = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(content);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
