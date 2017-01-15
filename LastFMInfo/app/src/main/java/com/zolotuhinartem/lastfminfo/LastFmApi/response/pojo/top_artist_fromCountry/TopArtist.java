package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_artist_fromCountry;

/**
 * Created by Dr on 10-Jan-17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopArtist {

    @SerializedName("topartists")
    @Expose
    private Topartists topartists;

    public Topartists getTopartists() {
        return topartists;
    }

    public void setTopartists(Topartists topartists) {
        this.topartists = topartists;
    }

}