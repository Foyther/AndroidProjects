package com.zolotuhinartem.lastfminfo.async;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.zolotuhinartem.lastfminfo.utils.DebugUtils;
import com.zolotuhinartem.lastfminfo.LastFmApi.LastFmApi;
import com.zolotuhinartem.lastfminfo.LastFmApi.LastFmApiCaller;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.TopTracksResponse;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_search.Albums;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Nurislam on 11.01.2017.
 */

public class TopTracksAsyncTaskFragment extends Fragment {

    private TopTracksCallback topTracksCallback;
    private TopTracksAsyncTask topTracksAsyncTask;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.topTracksCallback = null;
    }

    private void attach(Context context) {
        try {
            this.topTracksCallback = (TopTracksCallback) context;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    public void execute() {
        topTracksAsyncTask = new TopTracksAsyncTask();
        topTracksAsyncTask.execute();
    }

    public boolean isWorking() {
        return topTracksAsyncTask != null;
    }

    public interface TopTracksCallback {
        void onTopTracksCallback(TopTracksResponse topTracksResponse);
    }

    public class TopTracksAsyncTask extends AsyncTask<Void, Void, TopTracksResponse> {

        @Override
        protected TopTracksResponse doInBackground(Void... voids) {
            TopTracksResponse ttr = null;
            Response<TopTracksResponse> topTrackResponse;
            try {
                topTrackResponse = LastFmApi.getLastFmApiCaller().getTopTracks(LastFmApi.API_KEY).execute();
                ttr = topTrackResponse.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ttr;
        }

        @Override
        protected void onPostExecute(TopTracksResponse topTracksResponse) {
            topTracksAsyncTask = null;
            if (topTracksCallback != null) {
                topTracksCallback.onTopTracksCallback(topTracksResponse);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }
}
