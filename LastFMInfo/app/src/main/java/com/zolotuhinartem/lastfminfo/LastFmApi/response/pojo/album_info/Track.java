package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zolotuhinartem on 19.12.16.
 */

public class Track {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("duration")
    @Expose
    private  Long duration;

    @SerializedName("@attr")
    @Expose
    private Attr attr;

    @SerializedName("streamable")
    @Expose
    private Stramable stramable;

    @SerializedName("artist")
    @Expose
    private Artist artist;

    public Track(){}

    public Track(String name, String url, Long duration, Attr attr, Stramable stramable, Artist artist) {
        this.name = name;
        this.url = url;
        this.duration = duration;
        this.attr = attr;
        this.stramable = stramable;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    public Stramable getStramable() {
        return stramable;
    }

    public void setStramable(Stramable stramable) {
        this.stramable = stramable;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
