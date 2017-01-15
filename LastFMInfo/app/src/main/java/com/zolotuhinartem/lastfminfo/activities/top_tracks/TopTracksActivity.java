package com.zolotuhinartem.lastfminfo.activities.top_tracks;

import android.app.FragmentManager;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.Toast;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.TopTracksResponse;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_tracks.*;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.activities.track_info.TrackInfoActivity;
import com.zolotuhinartem.lastfminfo.async.TopTracksAsyncTaskFragment;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.TopTracksAdapter;

public class TopTracksActivity extends AppCompatActivity implements TopTracksAsyncTaskFragment.TopTracksCallback, TopTracksAdapter.OnTrackClickListenner {
    private RecyclerView rvTopTracks;
    private TopTracksAdapter adapter;
    private Tracks tracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_tracks);
        this.getTopTracksHolderFragment();

        Intent intent = getIntent();
        adapter = new TopTracksAdapter(this);
        rvTopTracks = (RecyclerView) findViewById(R.id.rv_tracks);
        rvTopTracks.setLayoutManager(new LinearLayoutManager(rvTopTracks.getContext()));

        if (savedInstanceState == null) {
            getTopTracksAsyncTaskFragment().execute();
        } else {
            load();
        }


    }

    public void load() {
        TopTracksHolderFragment fragment = getTopTracksHolderFragment();
        this.tracks = fragment.getTracks();
        adapter.setTopTracks(tracks.getTrack());

        rvTopTracks.setAdapter(adapter);
    }

    @Override
    public void onTopTracksCallback(TopTracksResponse topTracksResponse) {
        if (topTracksResponse != null) {
            tracks = topTracksResponse.getTracks();
            adapter.setTopTracks(tracks.getTrack());
            rvTopTracks.setAdapter(adapter);
        } else {this.finish();
        }
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTrackItemClick(Track track) {
        if (track != null) {
            Intent intent = new Intent(this, TrackInfoActivity.class);
            intent.putExtra(TrackInfoActivity.TRACK_NAME, track.getName());
            intent.putExtra(TrackInfoActivity.ARTIST_NAME, track.getArtist().getName());
            startActivity(intent);
        } else {
            showToast("this_does_not_have_a_web_page_please_try_select_another");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        save();
    }

    public void save() {
        TopTracksHolderFragment fragment = getTopTracksHolderFragment();
        fragment.setTracks(this.tracks);
    }

    public TopTracksHolderFragment getTopTracksHolderFragment() {
        TopTracksHolderFragment fragment = (TopTracksHolderFragment) getFragmentManager()
                .findFragmentByTag(TopTracksHolderFragment.class.getName());

        if (fragment == null) {
            fragment = new TopTracksHolderFragment();
            getFragmentManager().beginTransaction().add(fragment, TopTracksHolderFragment.class.getName()).commit();
        }
        return fragment;
    }

    public TopTracksAsyncTaskFragment getTopTracksAsyncTaskFragment() {
        TopTracksAsyncTaskFragment fragment = (TopTracksAsyncTaskFragment) getFragmentManager()
                .findFragmentByTag(TopTracksAsyncTaskFragment.class.getName());

        if (fragment == null) {
            fragment = new TopTracksAsyncTaskFragment();
            getFragmentManager().beginTransaction().add(fragment, TopTracksAsyncTaskFragment.class.getName()).commit();
        }

        return fragment;
    }
}
