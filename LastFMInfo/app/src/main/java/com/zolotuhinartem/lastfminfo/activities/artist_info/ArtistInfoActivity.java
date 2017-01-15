//package com.zolotuhinartem.lastfminfo.activities.artist_info;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.zolotuhinartem.lastfminfo.LastFmApi.response.ArtistInfoResponse;
//import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_info.Artist;
//import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_info.Image;
//import com.zolotuhinartem.lastfminfo.R;
//import com.zolotuhinartem.lastfminfo.async.ArtistInfoAsyncTaskFragment;
//
//
///**
// * Created by Dr on 22-Dec-16.
// */
//
//public class ArtistInfoActivity extends AppCompatActivity  implements ArtistInfoAsyncTaskFragment.ArtistInfoCallback{
//
//    public static final String ARTIST_ID = "artist_id";
//    private ProgressBar progressBar;
//    private TextView tvSummary;
//    private TextView tvArtistName;
//    private ImageView photo;
//    private Artist artist;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_album_info);
//
//        tvSummary = (TextView) findViewById(R.id.tv_activity_artist_info_summary);
//        tvArtistName = (TextView) findViewById(R.id.tv_activity_artist_info_name);
//
//        progressBar = (ProgressBar) findViewById(R.id.pb_activity_artist_info);
//        photo = (ImageView) findViewById(R.id.iv_activity_artist_info);
//
//        if (savedInstanceState == null) {
//            String mbid = getIntent().getStringExtra(ARTIST_ID);
//            getArtistInfoAsyncTaskFragment().execute(mbid);
//        } else {
//            load();
//        }
//
//        setProgress(getArtistInfoAsyncTaskFragment().isWorking());
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        save();
//    }
//
//    private void load(){
//        ArtistInfoHolderFragment fragment = getArtistInfoHolderFragment();
//        Artist artist = fragment.getArtist();
//        if (artist != null) {
//            this.artist = artist;
//            updateViews(artist);
//        }
//    }
//
//    private void updateViews(Artist artist) {
//        String artistName = artist.getName();
//        String listeners = artist.getStats().getListeners();
//        try {
//            artist.getBio().getSummary();
//        } catch (NullPointerException ex) {
//            ex.printStackTrace();
//        }
//
//        if (artistName != null) {
//            tvArtistName.setText(artistName);
//        }
//
//        if (listeners != null) {
//            tvSummary.setText(listeners);
//        }
//
//        Image image =  artist.getLargeImage();
//
//        if (image != null) {
//            Glide.with(this).load(image.getUrl()).fitCenter().into(photo);
//        }
//
//    }
//
//    private void save(){
//        ArtistInfoHolderFragment fragment = getArtistInfoHolderFragment();
//        fragment.setArtist(artist);
//    }
//
//    private ArtistInfoHolderFragment getArtistInfoHolderFragment(){
//        ArtistInfoHolderFragment fragment = (ArtistInfoHolderFragment) getFragmentManager()
//                .findFragmentByTag(ArtistInfoHolderFragment.class.getName());
//
//        if (fragment == null) {
//            fragment = new ArtistInfoHolderFragment();
//            getFragmentManager().beginTransaction().add(fragment, ArtistInfoHolderFragment.class.getName()).commit();
//        }
//
//        return fragment;
//    }
//
//    private ArtistInfoAsyncTaskFragment getArtistInfoAsyncTaskFragment(){
//        ArtistInfoAsyncTaskFragment fragment = (ArtistInfoAsyncTaskFragment) getFragmentManager()
//                .findFragmentByTag(ArtistInfoAsyncTaskFragment.class.getName());
//
//        if (fragment == null) {
//            fragment = new ArtistInfoAsyncTaskFragment();
//            getFragmentManager().beginTransaction().add(fragment, ArtistInfoAsyncTaskFragment.class.getName()).commit();
//        }
//
//        return fragment;
//    }
//
//    public void setProgress(boolean isLoading) {
//        if (isLoading) {
//             progressBar.setVisibility(View.VISIBLE);
//             photo.setVisibility(View.INVISIBLE);
//        } else {
//            progressBar.setVisibility(View.GONE);
//            photo.setVisibility(View.VISIBLE);
//
//        }
//    }
//
//    @Override
//    public void onArtistInfoCallback(ArtistInfoResponse artistInfoResponse) {
//        setProgress(false);
//        if (artistInfoResponse.getCode() < 400) {
//            Artist artist = artistInfoResponse.getArtist();
//            if (artist != null) {
//                this.artist = artist;
//                save();
//                updateViews(artist);
//            }
//        }
//    }
//}
