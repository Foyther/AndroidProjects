package com.zolotuhinartem.lastfminfo.activities.searched_artists;

import android.app.Fragment;
import android.os.Bundle;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_search.Artist;

import java.util.List;

/**
 * Created by Dr on 22-Dec-16.
 */

public class SearchedArtistsHolderFragment extends Fragment {
  //  public static final String TAG = "SearchedArtistsHolderFragment";
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
