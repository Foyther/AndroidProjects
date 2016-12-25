package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dr on 18-Dec-16.
 */

public class ArtistMatches {

    @SerializedName("artist")
    @Expose
    private List<Artist> artists = null;

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtist(List<Artist> artists) {
        this.artists = artists;
    }

}