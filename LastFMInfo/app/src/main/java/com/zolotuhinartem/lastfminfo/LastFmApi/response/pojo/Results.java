package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public class Results {

    @SerializedName("opensearch:Query")
    @Expose
    private OpensearchQuery opensearchQuery;

    @SerializedName("opensearch:totalResults")
    @Expose
    private Long opensearchTotalResults;

    @SerializedName("opensearch:startIndex")
    @Expose
    private Long opensearchStartIndex;

    @SerializedName("opensearch:itemsPerPage")
    @Expose
    private Long opensearchItemsPerPage;

    @SerializedName("albummatches")
    @Expose
    private AlbumMatches albumMatches;

    @SerializedName("@attr")
    @Expose
    private Attr attr;

    public Results(){

    }

    public Results(OpensearchQuery opensearchQuery, Long opensearchTotalResults, Long opensearchStartIndex, Long opensearchItemsPerPage, AlbumMatches albumMatches, Attr attr) {
        this.opensearchQuery = opensearchQuery;
        this.opensearchTotalResults = opensearchTotalResults;
        this.opensearchStartIndex = opensearchStartIndex;
        this.opensearchItemsPerPage = opensearchItemsPerPage;
        this.albumMatches = albumMatches;
        this.attr = attr;
    }

    public OpensearchQuery getOpensearchQuery() {
        return opensearchQuery;
    }

    public void setOpensearchQuery(OpensearchQuery opensearchQuery) {
        this.opensearchQuery = opensearchQuery;
    }

    public Long getOpensearchTotalResults() {
        return opensearchTotalResults;
    }

    public void setOpensearchTotalResults(Long opensearchTotalResults) {
        this.opensearchTotalResults = opensearchTotalResults;
    }

    public Long getOpensearchStartIndex() {
        return opensearchStartIndex;
    }

    public void setOpensearchStartIndex(Long opensearchStartIndex) {
        this.opensearchStartIndex = opensearchStartIndex;
    }

    public Long getOpensearchItemsPerPage() {
        return opensearchItemsPerPage;
    }

    public void setOpensearchItemsPerPage(Long opensearchItemsPerPage) {
        this.opensearchItemsPerPage = opensearchItemsPerPage;
    }

    public AlbumMatches getAlbumMatches() {
        return albumMatches;
    }

    public void setAlbumMatches(AlbumMatches albumMatches) {
        this.albumMatches = albumMatches;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }
}
