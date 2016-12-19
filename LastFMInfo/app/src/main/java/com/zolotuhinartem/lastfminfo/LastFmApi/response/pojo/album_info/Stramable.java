package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zolotuhinartem on 19.12.16.
 */

public class Stramable {

    @SerializedName("#text")
    @Expose
    private String text;

    @SerializedName("fulltrack")
    @Expose
    private String fulltrack;

    public Stramable(){}

    public Stramable(String text, String fulltrack) {
        this.text = text;
        this.fulltrack = fulltrack;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFulltrack() {
        return fulltrack;
    }

    public void setFulltrack(String fulltrack) {
        this.fulltrack = fulltrack;
    }
}
