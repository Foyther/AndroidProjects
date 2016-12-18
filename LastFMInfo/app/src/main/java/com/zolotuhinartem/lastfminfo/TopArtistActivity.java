package com.zolotuhinartem.lastfminfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.LastFmApi;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.LastFmApiCaller;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.TopArtists;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.TopArtists_;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.TopArtistsAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dr on 18-Dec-16.
 */

public class TopArtistActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private TopArtistsAdapter adapter;
    private ArrayList<TopArtists> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_top_artists);
        initViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_artistOrBand);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadJSON("spain");

    }

    private void loadJSON(String countryName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws.audioscrobbler.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LastFmApiCaller caller = retrofit.create(LastFmApiCaller.class);
        Call<TopArtists_> call = caller.searchTopArtists(countryName, LastFmApi.API_KEY);
        call.enqueue(new Callback<TopArtists_>() {
            @Override
            public void onResponse(Call<TopArtists_> call, Response<TopArtists_> response) {
                TopArtists_ artists = response.body();
                data = new ArrayList<TopArtists>(Arrays.asList(artists.getTopartists()));
                adapter = new TopArtistsAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<TopArtists_> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }

        });
    }
}
