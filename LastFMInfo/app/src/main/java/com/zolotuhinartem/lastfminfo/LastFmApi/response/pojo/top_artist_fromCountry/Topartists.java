package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_artist_fromCountry;

/**
 * Created by Dr on 10-Jan-17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Topartists {

    @SerializedName("artist")
    @Expose
    private List<Artist> artists = null;
    @SerializedName("@attr")
    @Expose
    private Attr attr;

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

}