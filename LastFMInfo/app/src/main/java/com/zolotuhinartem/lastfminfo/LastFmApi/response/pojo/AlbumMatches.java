package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public class AlbumMatches {


    @SerializedName("album")
    @Expose
    private List<Album> album = new ArrayList<>();


    public AlbumMatches(){}

    public AlbumMatches(List<Album> album) {
        this.album = album;
    }

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }
}
