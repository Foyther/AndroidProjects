package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
