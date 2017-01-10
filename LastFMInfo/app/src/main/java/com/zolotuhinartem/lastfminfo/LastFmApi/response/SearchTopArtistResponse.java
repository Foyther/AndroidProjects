package com.zolotuhinartem.lastfminfo.LastFmApi.response;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_artist_fromCountry.Topartists;

/**
 * Created by Dr on 10-Jan-17.
 */

public class SearchTopArtistResponse {
        private Topartists topartists;
        private int code;

    public Topartists getTopartists() {
        return topartists;
    }

    public void setTopartists(Topartists topartists) {
        this.topartists = topartists;
    }

    public SearchTopArtistResponse(int code, Topartists topartists) {
        this.code = code;
        this.topartists = topartists;
    }

    public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
