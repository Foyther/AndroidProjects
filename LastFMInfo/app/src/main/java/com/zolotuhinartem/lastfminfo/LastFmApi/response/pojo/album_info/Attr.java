package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zolotuhinartem on 19.12.16.
 */

public class Attr {

    @SerializedName("rank")
    @Expose
    private Long rank;

    public Attr(){}

    public Attr(Long rank) {
        this.rank = rank;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }
}
