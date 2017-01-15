package com.zolotuhinartem.lastfminfo.activities.track_info;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.TrackInfoResponse;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.track_info.*;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.async.TrackInfoAsyncTaskFragment;

import java.util.List;

public class TrackInfoActivity extends AppCompatActivity implements TrackInfoAsyncTaskFragment.TrackInfoCallback {

    public static final String TRACK_NAME = "track_id";
    public static final String ARTIST_NAME = "artist_id";
    private ProgressBar progressBar;
    private NestedScrollView nestedScrollView;
    private TextView tvArtistName;
    private TextView tvTrackName;
    private TextView tvTrackListenners;
    private TextView tvTrackAlbumName;
    private TextView tvTrackContent;
    private ImageView ivCover;
    private Track track;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_info);


        tvTrackName = (TextView) findViewById(R.id.tv_activity_track_info_track_name);
        tvArtistName = (TextView) findViewById(R.id.tv_activity_track_info_artist_name);
        tvTrackListenners = (TextView) findViewById(R.id.tv_activity_track_info_listenners);
        tvTrackAlbumName = (TextView) findViewById(R.id.tv_activity_track_info_track_album_name);
        tvTrackContent = (TextView) findViewById(R.id.tv_activity_track_info_content);

        progressBar = (ProgressBar) findViewById(R.id.pb_activity_track_info);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nsv_activity_track_info);

        ivCover = (ImageView) findViewById(R.id.iv_activity_track_info_cover);

        if (savedInstanceState == null) {
            String trackName = getIntent().getStringExtra(TRACK_NAME);
            String artistName = getIntent().getStringExtra(ARTIST_NAME);
            getTrackInfoAsyncTaskFragment().execute(trackName, artistName);
        } else {
            load();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        save();
    }

    private void load() {
        TrackInfoHolderFragment trackInfoHolderFragment = getTrackInfoHolderFragment();
        Track track = trackInfoHolderFragment.getTrack();
        if (track != null) {
            this.track = track;
            updateViews(track);
        }
    }

    private void updateViews(Track track) {
        String artistName = track.getArtist().getName();
        String trackName = track.getName();
        String trackListeners = track.getListeners();


        if (artistName != null) {
            tvArtistName.setText(artistName);
        }

        if (trackName != null) {
            tvTrackName.setText(trackName);
        }

        if (trackListeners != null) {
            tvTrackListenners.setText(trackListeners);
        }

        if (track.getAlbum() != null && track.getAlbum().getTitle() != null) {
            String trackAlbumName = track.getAlbum().getTitle();
            tvTrackAlbumName.setText(trackAlbumName);
        } else tvTrackAlbumName.setText("Don't have album");

        if (track.getWiki() != null && track.getWiki().getSummary() != null) {
            String trackContent = track.getWiki().getSummary();
            tvTrackContent.setText(trackContent);
        }


        if (track.getAlbum() != null) {
            List<Image> image = track.getAlbum().getImage();

            if (image != null) {
                Glide.with(this).load(image.get(image.size() - 1).getText()).fitCenter().into(ivCover);
            }
        } else {
            ivCover.setImageResource(R.drawable.lastfm_icon);
        }

    }

    private void save() {
        TrackInfoHolderFragment trackInfoHolderFragment = getTrackInfoHolderFragment();
        trackInfoHolderFragment.setTrack(this.track);
    }

    private TrackInfoAsyncTaskFragment getTrackInfoAsyncTaskFragment() {
        TrackInfoAsyncTaskFragment fragment = (TrackInfoAsyncTaskFragment) getFragmentManager()
                .findFragmentByTag(TrackInfoAsyncTaskFragment.class.getName());

        if (fragment == null) {
            fragment = new TrackInfoAsyncTaskFragment();
            getFragmentManager().beginTransaction().add(fragment, TrackInfoAsyncTaskFragment.class.getName()).commit();
        }

        return fragment;
    }

    private TrackInfoHolderFragment getTrackInfoHolderFragment() {
        TrackInfoHolderFragment fragment = (TrackInfoHolderFragment) getFragmentManager()
                .findFragmentByTag(TrackInfoHolderFragment.class.getName());

        if (fragment == null) {
            fragment = new TrackInfoHolderFragment();
            getFragmentManager().beginTransaction().add(fragment, TrackInfoHolderFragment.class.getName()).commit();
        }

        return fragment;
    }

    public void setProgress(boolean isLoading) {
        if (isLoading) {
            nestedScrollView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            nestedScrollView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onTrackInfoCallback(TrackInfoResponse trackInfoResponse) {
        if (trackInfoResponse != null) {
            setProgress(false);
            Track track = trackInfoResponse.getTrack();
            if (track != null) {
                this.track = track;
                save();
                updateViews(track);
            }
        } else this.finish();
    }
}