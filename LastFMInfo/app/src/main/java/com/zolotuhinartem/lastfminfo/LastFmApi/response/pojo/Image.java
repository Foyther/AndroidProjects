package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public class Image {

    @SerializedName("#text")
    @Expose
    private String url;


    @SerializedName("size")
    @Expose
    private String size;

    public Image(){}

    public Image(String url, String size) {
        this.url = url;
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
