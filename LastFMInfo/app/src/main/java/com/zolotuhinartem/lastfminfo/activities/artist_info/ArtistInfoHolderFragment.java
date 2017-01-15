package com.zolotuhinartem.lastfminfo.activities.artist_info;

import android.app.Fragment;
import android.os.Bundle;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_info.Artist;

/**
 * Created by Dr on 22-Dec-16.
 */

public class ArtistInfoHolderFragment extends Fragment {

    private Artist artist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}

