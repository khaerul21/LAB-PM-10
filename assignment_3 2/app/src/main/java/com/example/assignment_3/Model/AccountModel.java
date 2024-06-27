package com.example.assignment_3.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class AccountModel implements Parcelable {
    protected AccountModel(Parcel in) {
        image = in.readInt();
        username = in.readString();
        caption = in.readString();
        storyBackground = in.readInt();
        followers = in.readString();
        following = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(username);
        dest.writeString(caption);
        dest.writeInt(storyBackground);
        dest.writeString(followers);
        dest.writeString(following);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AccountModel> CREATOR = new Creator<AccountModel>() {
        @Override
        public AccountModel createFromParcel(Parcel in) {
            return new AccountModel(in);
        }

        @Override
        public AccountModel[] newArray(int size) {
            return new AccountModel[size];
        }
    };

    public Integer getImage() {
        return image;
    }

    public AccountModel(int image, String username, String caption, int storyBackground, String followers, String following) {
        this.image = image;
        this.username = username;
        this.caption = caption;
        this.storyBackground = storyBackground;
        this.followers = followers;
        this.following = following;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private Integer image;

    public Integer getStoryBackground() {
        return storyBackground;
    }

    public void setStoryBackground(Integer storyBackground) {
        this.storyBackground = storyBackground;
    }

    private Integer storyBackground;
    private String username;
    private String caption;

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    private String followers;
    private String following;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

}
