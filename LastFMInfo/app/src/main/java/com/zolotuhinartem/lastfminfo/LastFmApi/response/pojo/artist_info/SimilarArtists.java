
package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimilarArtists {

    @SerializedName("artist")
    @Expose
    private List<SimilarArtist> artist = null;

    public List<SimilarArtist> getArtist() {
        return artist;
    }

    public void setArtist(List<SimilarArtist> artist) {
        this.artist = artist;
    }

}
