package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_artist_fromCountry;

/**
 * Created by Dr on 10-Jan-17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Image;

import java.util.List;

public class Artist implements Comparable<Artist> {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("streamable")
    @Expose
    private String streamable;
    @SerializedName("image")
    @Expose
    private List<Image> images = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public List<Image> getImage() {
        return images;
    }

    public void setImage(List<Image> image) {
        this.images = images;
    }

    public Image getSmallImage(){
        return getImageSize("small");
    }

    public Image getMediumImage(){
        return getImageSize("medium");
    }

    public Image getLargeImage(){
        return getImageSize("large");
    }

    public Image getExtralargeImage(){
        return getImageSize("extralarge");
    }



    private Image getImageSize(String string){
        for (Image i: this.images) {
            if (i.getSize().equals(string)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public int compareTo(Artist artist) {
        if (artist.getMbid().length() <= 0) {
            return  -1;
        } else {
            if (this.getMbid().length() <= 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}