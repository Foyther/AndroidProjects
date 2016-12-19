package com.zolotuhinartem.lastfminfo.activities.searched_albums;

import android.app.Fragment;
import android.os.Bundle;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_search.Album;

import java.util.List;

/**
 * Created by zolotuhinartem on 19.12.16.
 */

public class SearchedAlbumsHolderFragment extends Fragment {

    private List<Album> albums;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
