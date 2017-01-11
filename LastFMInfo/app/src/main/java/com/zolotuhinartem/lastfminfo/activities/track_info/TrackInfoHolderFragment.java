package com.zolotuhinartem.lastfminfo.activities.track_info;

import android.app.Fragment;
import android.os.Bundle;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.track_info.*;

/**
 * Created by Nurislam on 11.01.2017.
 */

public class TrackInfoHolderFragment extends Fragment{
    private Track track;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
