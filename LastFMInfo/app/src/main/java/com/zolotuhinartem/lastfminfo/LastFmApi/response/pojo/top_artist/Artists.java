package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_artist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_search.Results;

/**
 * Created by Dr on 18-Dec-16.
 */

public class Artists {

    @SerializedName("results")
    @Expose
    private Results results;

    public Artists() {
    }

    public Artists(Results results) {
        this.results = results;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }


}
