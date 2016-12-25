package com.zolotuhinartem.lastfminfo.LastFmApi.response;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_info.Artist;

/**
 * Created by Dr on 22-Dec-16.
 */

public class ArtistInfoResponse {
    private Artist artist;
    private int code;

    public ArtistInfoResponse(Artist artist, int code) {
        this.artist = artist;
        this.code = code;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
