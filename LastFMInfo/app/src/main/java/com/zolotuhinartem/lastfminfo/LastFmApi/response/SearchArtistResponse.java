package com.zolotuhinartem.lastfminfo.LastFmApi.response;


import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Artists;

/**
 * Created by Dr on 18-Dec-16.
 */

public class SearchArtistResponse {
    private Artists artist;
    private int code;

    public SearchArtistResponse(Artists artist, int code) {
        this.artist = artist;
        this.code = code;
    }

    public Artists getArtist() {
        return artist;
    }

    public void setArtist(Artists artist) {
        this.artist = artist;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
