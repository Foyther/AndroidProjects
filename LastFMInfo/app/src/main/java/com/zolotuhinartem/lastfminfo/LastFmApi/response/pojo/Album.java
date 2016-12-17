package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public class Album {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("artist")
    @Expose
    private String artistName;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("image")
    @Expose
    private List<Image> image = new ArrayList<>();

    @SerializedName("streamable")
    @Expose
    private Long stramable;

    @SerializedName("mbid")
    @Expose
    private String mbid;

    public Album(){}

    public Album(String name, String artistName, String url, List<Image> image, Long stramable, String mbid) {
        this.name = name;
        this.artistName = artistName;
        this.url = url;
        this.image = image;
        this.stramable = stramable;
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Long getStramable() {
        return stramable;
    }

    public void setStramable(Long stramable) {
        this.stramable = stramable;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }
}
