package com.zolotuhinartem.lastfminfo.activities.searched_top_artists;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.SearchArtistResponse;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_search.Artist;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.activities.SearchActivity;
import com.zolotuhinartem.lastfminfo.activities.artist_info.ArtistInfoActivity;
import com.zolotuhinartem.lastfminfo.async.SearchTopArtistAsyncTaskFragment;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.ArtistItemAdapter;

/**
 * Created by Dr on 25-Dec-16.
 */

public class SearchedTopArtistsActivity extends AppCompatActivity implements ArtistItemAdapter.OnArtistItemClickListener, SearchTopArtistAsyncTaskFragment.SearchTopArtistCallback {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SearchTopArtistAsyncTaskFragment fragment;
    private ArtistItemAdapter adapter;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_artists);


        progressBar = (ProgressBar) findViewById(R.id.pb_activity_searched_artists);
        recyclerView = (RecyclerView) findViewById(R.id.rv_activity_searched_artists);
        imageView = (ImageView) findViewById(R.id.icon_lastfm);


        adapter = new ArtistItemAdapter();
        adapter.setListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fragment = getFragment();

        if (savedInstanceState == null) {
            fragment.execute(getIntent().getStringExtra(SearchActivity.NAME_FOR_SEARCH));
        } else {
            SearchedTopArtistHolderFragment fragment = getFragmentHolder();
            adapter.setList(fragment.getArtists());
        }

        setProgressBar(fragment.isWorking());
    }


    private SearchedTopArtistHolderFragment getFragmentHolder() {
        SearchedTopArtistHolderFragment fragment = (SearchedTopArtistHolderFragment) getFragmentManager().findFragmentByTag(SearchedTopArtistHolderFragment.class.getName());
        if (fragment == null) {
            fragment = new SearchedTopArtistHolderFragment();
            getFragmentManager().beginTransaction()
                    .add(fragment, SearchedTopArtistHolderFragment.class.getName())
                    .commit();
        }
        return fragment;
    }

    private SearchTopArtistAsyncTaskFragment getFragment() {
        SearchTopArtistAsyncTaskFragment fragment = (SearchTopArtistAsyncTaskFragment) getFragmentManager().findFragmentByTag(SearchTopArtistAsyncTaskFragment.class.getName());
        if (fragment == null) {
            fragment = new SearchTopArtistAsyncTaskFragment();
            getFragmentManager().beginTransaction()
                    .add(fragment, SearchTopArtistAsyncTaskFragment.class.getName())
                    .commit();
        }
        return fragment;
    }

    public void setProgressBar(boolean load) {
        if (load) {
            progressBar.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getFragmentHolder().setArtists(adapter.getList());
        super.onSaveInstanceState(outState);

    }


    @Override
    public void onSearchTopArtistCallback(SearchArtistResponse searchArtistResponse) {
        if (searchArtistResponse.getCode() < 400){
            if (searchArtistResponse.getArtists() != null){
                adapter.setList(searchArtistResponse.getArtists().getResults().getArtistmatches().getArtists());
            }
        }
        setProgressBar(false);
    }

    @Override
    public void onArtistItemClick(Artist artist) {
        if (artist != null) {
            if (artist.getMbid().length() > 0) {
                Intent intent = new Intent(this, ArtistInfoActivity.class);
                intent.putExtra("artist", artist.getMbid());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
