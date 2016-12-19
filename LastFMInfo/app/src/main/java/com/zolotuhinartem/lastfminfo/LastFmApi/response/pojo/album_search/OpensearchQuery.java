package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public class OpensearchQuery {

    @SerializedName("#text")
    @Expose
    private String text;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("searchTerms")
    @Expose
    private String searchTerms;

    @SerializedName("startPage")
    @Expose
    private Long startPage;

    public OpensearchQuery(){}

    public OpensearchQuery(String text, String role, String searchTerms, Long startPage) {
        this.text = text;
        this.role = role;
        this.searchTerms = searchTerms;
        this.startPage = startPage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public Long getStartPage() {
        return startPage;
    }

    public void setStartPage(Long startPage) {
        this.startPage = startPage;
    }
}
