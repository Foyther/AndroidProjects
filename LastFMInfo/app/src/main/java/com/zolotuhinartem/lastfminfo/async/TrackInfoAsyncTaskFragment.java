package com.zolotuhinartem.lastfminfo.async;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.zolotuhinartem.lastfminfo.LastFmApi.LastFmApi;
import com.zolotuhinartem.lastfminfo.LastFmApi.LastFmApiCaller;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.TrackInfoResponse;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.track_info.Track;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Nurislam on 11.01.2017.
 */

public class TrackInfoAsyncTaskFragment extends Fragment{
    
    private TrackInfoAsyncTaskFragment.TrackInfoAsyncTask trackInfoAsyncTask;
    private TrackInfoAsyncTaskFragment.TrackInfoCallback trackInfoCallback;
    private String trackName;
    private String artistName;

    public void execute(String trackName, String artistName){
        this.trackName = trackName;
        this.artistName = artistName;
        if (this.trackInfoAsyncTask == null){
            this.trackInfoAsyncTask = new TrackInfoAsyncTaskFragment.TrackInfoAsyncTask();
            this.trackInfoAsyncTask.execute();
        }
    }

    public boolean isWorking(){
        return trackInfoAsyncTask != null;
    }

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
        this.trackInfoCallback = null;
    }

    private void attach(Context context) {
        try {
            this.trackInfoCallback = (TrackInfoAsyncTaskFragment.TrackInfoCallback) context;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    public interface TrackInfoCallback{
        void onTrackInfoCallback(TrackInfoResponse trackInfoResponse);
    }

    public class TrackInfoAsyncTask extends AsyncTask<Void, Void, TrackInfoResponse> {

        @Override
        protected TrackInfoResponse doInBackground(Void... voids) {
            TrackInfoResponse trackInfoResponse = null;
            if (artistName != null && trackName != null) {
                    LastFmApiCaller caller = LastFmApi.getLastFmApiCaller();
                    Call<TrackInfoResponse> trackCall = caller.getTrackInfo(artistName, trackName, LastFmApi.API_KEY);
                    try {
                        Response<TrackInfoResponse> trackResponse = trackCall.execute();
                        trackInfoResponse = trackResponse.body();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            return trackInfoResponse;
        }

        @Override
        protected void onPostExecute(TrackInfoResponse trackInfoResponse) {
            trackInfoAsyncTask = null;
            if (trackInfoCallback != null) {
                trackInfoCallback.onTrackInfoCallback(trackInfoResponse);
            }
        }
    }
}
