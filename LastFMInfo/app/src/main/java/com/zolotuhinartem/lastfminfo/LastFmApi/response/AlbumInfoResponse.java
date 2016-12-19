package com.zolotuhinartem.lastfminfo.LastFmApi.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info.Album;

/**
 * Created by zolotuhinartem on 19.12.16.
 */

public class AlbumInfoResponse {

    @SerializedName("album")
    @Expose
    private Album album;

    public AlbumInfoResponse(){}

    public AlbumInfoResponse(Album album) {
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
