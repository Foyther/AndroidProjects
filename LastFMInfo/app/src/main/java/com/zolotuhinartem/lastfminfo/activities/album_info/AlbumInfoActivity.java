package com.zolotuhinartem.lastfminfo.activities.album_info;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.AlbumInfoResponse;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Image;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info.Album;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info.Track;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.activities.artist_info.ArtistInfoActivity;
import com.zolotuhinartem.lastfminfo.activities.searched_albums.SearchedAlbumsActivity;
import com.zolotuhinartem.lastfminfo.activities.searched_artists.SearchedArtistsActivity;
import com.zolotuhinartem.lastfminfo.activities.track_info.TrackInfoActivity;
import com.zolotuhinartem.lastfminfo.async.AlbumInfoAsyncTaskFragment;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.AlbumInfoTrackItemAdapter;

public class AlbumInfoActivity extends AppCompatActivity implements AlbumInfoAsyncTaskFragment.AlbumInfoCallback, View.OnClickListener, AlbumInfoTrackItemAdapter.AlbumInfoTrackItemOnClickListener {
    //test  2
    public static final String ALBUM_ID = "album_id";
    private RecyclerView rvTrackList;
    private ProgressBar progressBar;
    private NestedScrollView nestedScrollView;
    private TextView tvContent;
    private TextView tvArtistName;
    private TextView tvAlbumName;
    private TextView tvListeners;
    private TextView tvPlayCount;
    private ImageView ivCover;
    private AlbumInfoTrackItemAdapter trackListAdapter;

    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);

        tvContent = (TextView) findViewById(R.id.tv_activity_album_info_content);
        tvListeners = (TextView) findViewById(R.id.tv_activity_album_info_listeners);
        tvPlayCount = (TextView) findViewById(R.id.tv_activity_album_info_playcount);
        tvAlbumName = (TextView) findViewById(R.id.tv_activity_album_info_album_name);
        tvArtistName = (TextView) findViewById(R.id.tv_activity_album_info_artist_name);
        tvArtistName.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.pb_activity_album_info);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nsv_activity_album_info);

        ivCover = (ImageView) findViewById(R.id.iv_activity_album_info_cover);

        rvTrackList = (RecyclerView) findViewById(R.id.rv_activity_album_info_track_list);
        trackListAdapter = new AlbumInfoTrackItemAdapter();
        trackListAdapter.setListener(this);
        rvTrackList.setLayoutManager(new LinearLayoutManager(this));
        rvTrackList.setAdapter(trackListAdapter);

        AlbumInfoAsyncTaskFragment albumInfoAsyncTaskFragment = getAlbumInfoAsyncTaskFragment();

        if (savedInstanceState == null) {
            String mbid = getIntent().getStringExtra(ALBUM_ID);
            albumInfoAsyncTaskFragment.execute(mbid);
        } else {
            load();
        }

        this.setProgress(albumInfoAsyncTaskFragment.isWorking());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        save();
    }

    private void load() {
        AlbumInfoHolderFragment albumInfoHolderFragment = getAlbumInfoHolderFragment();
        Album album = albumInfoHolderFragment.getAlbum();
        if (album != null) {
            this.album = album;
            updateViews(album);
        }
    }

    private void updateViews(Album album) {

        String artistName = album.getArtist();
        String albumName = album.getName();

        String content = null;
        try {
            content = album.getWiki().getContent();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        if (artistName != null) {
            tvArtistName.setText(artistName);
        }

        if (albumName != null) {
            tvAlbumName.setText(albumName);
        }

        if (content != null) {
            tvContent.setText(content);
        }
        trackListAdapter.setList(album.getTracks().getTracks());


        tvListeners.setText(Long.toString(album.getListeners()));
        tvPlayCount.setText(Long.toString(album.getPlaycount()));

        Image image = album.getExtralargeImage();

        if (image != null) {
            Glide.with(this).load(image.getUrl()).fitCenter().into(ivCover);
        }

    }

    private void save() {
        AlbumInfoHolderFragment albumInfoHolderFragment = getAlbumInfoHolderFragment();
        albumInfoHolderFragment.setAlbum(this.album);
    }

    private AlbumInfoAsyncTaskFragment getAlbumInfoAsyncTaskFragment() {
        AlbumInfoAsyncTaskFragment fragment = (AlbumInfoAsyncTaskFragment) getFragmentManager()
                .findFragmentByTag(AlbumInfoAsyncTaskFragment.class.getName());

        if (fragment == null) {
            fragment = new AlbumInfoAsyncTaskFragment();
            getFragmentManager().beginTransaction().add(fragment, AlbumInfoAsyncTaskFragment.class.getName()).commit();
        }

        return fragment;
    }

    private AlbumInfoHolderFragment getAlbumInfoHolderFragment() {
        AlbumInfoHolderFragment fragment = (AlbumInfoHolderFragment) getFragmentManager()
                .findFragmentByTag(AlbumInfoHolderFragment.class.getName());

        if (fragment == null) {
            fragment = new AlbumInfoHolderFragment();
            getFragmentManager().beginTransaction().add(fragment, AlbumInfoHolderFragment.class.getName()).commit();
        }

        return fragment;
    }

    public void setProgress(boolean isLoading) {
        int statusForView = isLoading ? View.GONE : View.VISIBLE;
        int statusForProgressBar = isLoading ? View.VISIBLE : View.GONE;
        tvPlayCount.setVisibility(statusForView);
        tvContent.setVisibility(statusForView);
        tvListeners.setVisibility(statusForView);
        tvAlbumName.setVisibility(statusForView);
        tvArtistName.setVisibility(statusForView);
        nestedScrollView.setVisibility(statusForView);
        rvTrackList.setVisibility(statusForView);
        progressBar.setVisibility(statusForProgressBar);
    }

    @Override
    public void onAlbumInfoCallback(AlbumInfoResponse albumInfoResponse) {
        setProgress(false);
        if (albumInfoResponse.getCode() < 400) {
            Album album = albumInfoResponse.getAlbum();
            if (album != null) {
                this.album = album;
                save();
                updateViews(album);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (album != null) {
            switch (view.getId()) {
                case R.id.tv_activity_album_info_artist_name:
                    Intent intent = new Intent(this, SearchedArtistsActivity.class);
                    intent.putExtra(SearchedArtistsActivity.ARTIST_NAME, this.album.getArtist());
                    startActivity(intent);
            }
        }
    }

    @Override
    public void onAlbumInfoTrackItemClick(Track track) {
        Intent intent = new Intent(this, TrackInfoActivity.class);
        intent.putExtra(TrackInfoActivity.ARTIST_NAME, track.getArtist().getName());
        intent.putExtra(TrackInfoActivity.TRACK_NAME, track.getName());
        startActivity(intent);
    }
}
