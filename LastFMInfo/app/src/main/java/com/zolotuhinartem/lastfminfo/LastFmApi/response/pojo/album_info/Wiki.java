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

    public Wiki(){}

    public Wiki(String published, String summary, String content) {
        this.published = published;
        this.summary = summary;
        this.content = content;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
