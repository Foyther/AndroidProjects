package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_artist;

/**
 * Created by Dr on 18-Dec-16.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopArtists_ {

    @SerializedName("topartists")
    @Expose
    private TopArtists topartists;

    public TopArtists getTopartists() {
        return topartists;
    }

    public void setTopartists(TopArtists topartists) {
        this.topartists = topartists;
    }

}