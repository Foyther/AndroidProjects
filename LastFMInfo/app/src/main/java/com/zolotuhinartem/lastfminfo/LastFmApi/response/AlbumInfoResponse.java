package com.zolotuhinartem.lastfminfo.LastFmApi.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info.Album;

/**
 * Created by zolotuhinartem on 19.12.16.
 */

public class AlbumInfoResponse {

    private Album album;

    private int code;

    public AlbumInfoResponse(){}

    public AlbumInfoResponse(Album album, int code) {
        this.album = album;
        this.code = code;
    }


    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
