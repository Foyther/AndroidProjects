package com.zolotuhinartem.lastfminfo.LastFmApi.response;


import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_search.Artists;

/**
 * Created by Dr on 18-Dec-16.
 */

public class SearchArtistResponse {
    private Artists artists;
    private int code;

    public SearchArtistResponse(Artists artists, int code) {
        this.artists = artists;
        this.code = code;
    }

    public Artists getArtists() {
        return artists;
    }

    public void setArtists(Artists artists) {
        this.artists = artists;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
