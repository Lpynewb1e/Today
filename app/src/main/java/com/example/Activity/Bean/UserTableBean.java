package com.example.Activity.Bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UserTableBean implements Parcelable {
    public static final Creator<UserTableBean> CREATOR = new Creator<UserTableBean>() {
        @Override
        public UserTableBean createFromParcel(Parcel in) {
            return new UserTableBean(in);
        }

        @Override
        public UserTableBean[] newArray(int size) {
            return new UserTableBean[size];
        }
    };

    private String id;
    private String name;
    private String password;
    private String subname;

    public UserTableBean() {
    }

    public UserTableBean(Parcel source) {
        id = source.readString();
        name = source.readString();
        password = source.readString();
        subname = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(subname);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }
}
