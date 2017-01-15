package com.zolotuhinartem.lastfminfo.activities.album_info;

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
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.async.AlbumInfoAsyncTaskFragment;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.AlbumInfoTrackItemAdapter;

public class AlbumInfoActivity extends AppCompatActivity implements AlbumInfoAsyncTaskFragment.AlbumInfoCallback {
    //test
    public static final String ALBUM_ID = "album_id";
    private RecyclerView rvTrackList;
    private ProgressBar progressBar;
    private NestedScrollView nestedScrollView;
    private TextView tvContent;
    private TextView tvArtistName;
    private TextView tvAlbumName;
    private ImageView ivCover;
    private AlbumInfoTrackItemAdapter trackListAdapter;

    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);

        tvContent = (TextView) findViewById(R.id.tv_activity_album_info_content);
        tvAlbumName = (TextView) findViewById(R.id.tv_activity_album_info_album_name);
        tvArtistName = (TextView)findViewById(R.id.tv_activity_album_info_artist_name);

        progressBar = (ProgressBar) findViewById(R.id.pb_activity_album_info);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nsv_activity_album_info);

        ivCover = (ImageView) findViewById(R.id.iv_activity_album_info_cover);

        rvTrackList = (RecyclerView) findViewById(R.id.rv_activity_album_info_track_list);
        trackListAdapter = new AlbumInfoTrackItemAdapter();
        rvTrackList.setLayoutManager(new LinearLayoutManager(this));
        rvTrackList.setAdapter(trackListAdapter);

        if (savedInstanceState == null) {
            String mbid = getIntent().getStringExtra(ALBUM_ID);
            getAlbumInfoAsyncTaskFragment().execute(mbid);
        } else {
            load();
        }

        setProgress(getAlbumInfoAsyncTaskFragment().isWorking());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        save();
    }

    private void load(){
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
            album.getWiki().getContent();
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

        Image image = album.getExtralargeImage();

        if (image != null) {
            Glide.with(this).load(image.getUrl()).fitCenter().into(ivCover);
        }

    }

    private void save(){
        AlbumInfoHolderFragment albumInfoHolderFragment = getAlbumInfoHolderFragment();
        albumInfoHolderFragment.setAlbum(this.album);
    }

    private AlbumInfoAsyncTaskFragment getAlbumInfoAsyncTaskFragment(){
        AlbumInfoAsyncTaskFragment fragment = (AlbumInfoAsyncTaskFragment) getFragmentManager()
                .findFragmentByTag(AlbumInfoAsyncTaskFragment.class.getName());

        if (fragment == null) {
            fragment = new AlbumInfoAsyncTaskFragment();
            getFragmentManager().beginTransaction().add(fragment, AlbumInfoAsyncTaskFragment.class.getName()).commit();
        }

        return fragment;
    }

    private AlbumInfoHolderFragment getAlbumInfoHolderFragment(){
        AlbumInfoHolderFragment fragment = (AlbumInfoHolderFragment) getFragmentManager()
                .findFragmentByTag(AlbumInfoHolderFragment.class.getName());

        if (fragment == null) {
            fragment = new AlbumInfoHolderFragment();
            getFragmentManager().beginTransaction().add(fragment, AlbumInfoHolderFragment.class.getName()).commit();
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
}
