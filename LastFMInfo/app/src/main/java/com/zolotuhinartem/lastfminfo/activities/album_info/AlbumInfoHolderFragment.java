package com.zolotuhinartem.lastfminfo.activities.album_info;

import android.app.Fragment;
import android.os.Bundle;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info.Album;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info.Track;

import java.util.List;

/**
 * Created by zolotuhinartem on 19.12.16.
 */

public class AlbumInfoHolderFragment extends Fragment {

    private Album album;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
