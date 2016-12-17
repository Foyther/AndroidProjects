package com.zolotuhinartem.lastfminfo.LastFmApi.response;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Albums;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public class SearchAlbumResponse {

    private Albums albums;
    private int code;

    public SearchAlbumResponse(Albums albums, int code) {
        this.albums = albums;
        this.code = code;
    }

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
