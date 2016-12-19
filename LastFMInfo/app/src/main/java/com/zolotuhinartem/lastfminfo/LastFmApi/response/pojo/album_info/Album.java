package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Image;

import java.util.List;

/**
 * Created by zolotuhinartem on 19.12.16.
 */

public class Album implements Comparable<Album>{

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("artist")
    @Expose
    private  String artist;

    @SerializedName("mbid")
    @Expose
    private String mbid;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("image")
    @Expose
    private List<Image> images;

    @SerializedName("listeners")
    @Expose
    private Long listeners;

    @SerializedName("playcount")
    @Expose
    private Long playcount;

    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    @SerializedName("tags")
    @Expose
    private Tags tags;

    @SerializedName("wiki")
    @Expose
    private Wiki wiki;

    public Album(){}

    public Album(String name, String artist, String mbid, String url, List<Image> images, Long listeners, Long playcount, Tracks tracks, Tags tags, Wiki wiki) {
        this.name = name;
        this.artist = artist;
        this.mbid = mbid;
        this.url = url;
        this.images = images;
        this.listeners = listeners;
        this.playcount = playcount;
        this.tracks = tracks;
        this.tags = tags;
        this.wiki = wiki;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Long getListeners() {
        return listeners;
    }

    public void setListeners(Long listeners) {
        this.listeners = listeners;
    }

    public Long getPlaycount() {
        return playcount;
    }

    public void setPlaycount(Long playcount) {
        this.playcount = playcount;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
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

    public Image getMegaImage(){
        return getImageSize("mega");
    }



    private Image getImageSize(String string){
        for (Image i: this.getImages()) {
            if (i.getSize().equals(string)) {
                return i;
            }
        }
        return null;
    }


}
