package com.zolotuhinartem.lastfminfo.activities.top_tracks;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_search.Album;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_tracks.Track;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_tracks.Tracks;
import com.zolotuhinartem.lastfminfo.R;

import java.util.List;

/**
 * Created by Nurislam on 02.01.2017.
 */

public class TopTracksHolderFragment extends Fragment {
    private List<Track> tracks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
