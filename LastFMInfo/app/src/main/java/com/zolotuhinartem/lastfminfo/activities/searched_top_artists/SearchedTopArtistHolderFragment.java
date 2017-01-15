package com.zolotuhinartem.lastfminfo.activities.searched_top_artists;

import android.app.Fragment;
import android.os.Bundle;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_artist_fromCountry.Artist;

import java.util.List;

/**
 * Created by Dr on 25-Dec-16.
 */

public class SearchedTopArtistHolderFragment extends Fragment {
    private List<Artist> artists;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

}
