package com.zolotuhinartem.lastfminfo.LastFmApi.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.track_info.Track;

public class TrackInfoResponse {

    @SerializedName("track")
    @Expose
    private Track track;

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

}
