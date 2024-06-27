package com.example.assignment_5;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Account implements Parcelable {
    private String fullname, username, caption;

    public Account(String fullname, String username, String caption, Integer profile, Integer post) {
        this.fullname = fullname;
        this.username = username;
        this.caption = caption;
        this.profile = profile;
        this.post = post;
    }

    public Account(String fullname, String username, String caption, Integer profile, Uri addpost) {
        this.fullname = fullname;
        this.username = username;
        this.caption = caption;
        this.profile = profile;
        this.addpost = addpost;
    }

    protected Account(Parcel in) {
        fullname = in.readString();
        username = in.readString();
        caption = in.readString();
        if (in.readByte() == 0) {
            profile = null;
        } else {
            profile = in.readInt();
        }
        if (in.readByte() == 0) {
            post = null;
        } else {
            post = in.readInt();
        }
        addpost = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Uri getAddpost() {
        return addpost;
    }

    public void setAddpost(Uri addpost) {
        this.addpost = addpost;
    }

    private Integer profile, post;
    private Uri addpost;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(fullname);
        parcel.writeString(username);
        parcel.writeString(caption);
        if (profile == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(profile);
        }
        if (post == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(post);
        }
        parcel.writeParcelable(addpost, i);
    }
}