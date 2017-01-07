package com.zolotuhinartem.lastfminfo.activities.top_tracks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zolotuhinartem.lastfminfo.LastFmApi.LastFmApi;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.TopTrackResponse;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_tracks.Tracks;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.TopTracksAdapter;

import java.io.IOException;

import retrofit2.Response;

public class TopTracksActivity extends AppCompatActivity {
    RecyclerView rvTopTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_tracks);
        Intent intent = getIntent();
        rvTopTracks = (RecyclerView) findViewById(R.id.rv_tracks);
        rvTopTracks.setLayoutManager(new LinearLayoutManager(rvTopTracks.getContext()));
        TopTracksAdapter adapter = null;
        try {
            adapter = new TopTracksAdapter(getTracks().getTrack());
        } catch (IOException e) {
            e.printStackTrace();
        }
        rvTopTracks.setAdapter(adapter);
    }

    private Tracks getTracks() throws IOException {
        Response<TopTrackResponse> topTrackResponse;
        topTrackResponse = LastFmApi.getLastFmApiCaller().getTopTracks(LastFmApi.API_KEY).execute();
        return topTrackResponse.body().getTracks();
    }
}
