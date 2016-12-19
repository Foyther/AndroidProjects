package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public class Album implements Comparable<Album>{


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
        for (Image i: this.image) {
            if (i.getSize().equals(string)) {
                return i;
            }
        }
        return null;
    }


    @Override
    public int compareTo(Album album) {
        if (album.getMbid().length() <= 0) {
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
