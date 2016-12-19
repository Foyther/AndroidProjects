package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zolotuhinartem on 19.12.16.
 */

public class Wiki {

    @SerializedName("published")
    @Expose
    private String published;

    @SerializedName("summary")
    @Expose
    private String summary;

    @SerializedName("content")
    @Expose
    private String content;
}
