package com.zolotuhinartem.lastfminfo.async;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;

import com.zolotuhinartem.lastfminfo.LastFmApi.LastFmApi;
import com.zolotuhinartem.lastfminfo.LastFmApi.LastFmApiCaller;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.ArtistInfoResponse;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_info.Artist;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_info.Artists;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Dr on 22-Dec-16.
 */

public class ArtistInfoAsyncTaskFragment extends Fragment {
    private ArtistInfoCallback artistInfoCallback;
    private ArtistInfoAsyncTask artistInfoAsyncTask;

    public interface ArtistInfoCallback{
        void onArtistInfoCallback(ArtistInfoResponse artistInfoResponse);
    }

    public void execute(String mbid){
        if (this.artistInfoAsyncTask == null){
            this.artistInfoAsyncTask = new ArtistInfoAsyncTask();
            this.artistInfoAsyncTask.execute(mbid);
        }
    }

    public boolean isWorking(){
        return artistInfoAsyncTask != null;
    }

    private void attach(Context context){
        try {
            this.artistInfoCallback = (ArtistInfoCallback) context;
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }
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
        this.artistInfoCallback = null;
    }

    public class ArtistInfoAsyncTask extends AsyncTask<String, Void, ArtistInfoResponse>{

        @Override
        protected ArtistInfoResponse doInBackground(String... strings) {
            ArtistInfoResponse artistInfoResponse = null;
            if (strings != null) {
                if (strings[0] != null) {
                    LastFmApiCaller caller = LastFmApi.getLastFmApiCaller();
                    Call<Artists> call = caller.getArtistInfo(strings[0], LastFmApi.API_KEY);
                    Artist artist = null;
                    int code = 1000;
                    try {
                        Response<Artists> response = call.execute();
                        code = response.code();
                        Artists artists = response.body();
                        artist = artists.getArtist();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassCastException ex) {
                        ex.printStackTrace();
                    } catch (NullPointerException ex) {
                        ex.printStackTrace();
                    }
                    artistInfoResponse = new ArtistInfoResponse(artist, code);
                }
            }
            return artistInfoResponse;
        }

        @Override
        protected void onPostExecute(ArtistInfoResponse artistInfoResponse) {
            artistInfoAsyncTask = null;
            if (artistInfoCallback != null) {
                artistInfoCallback.onArtistInfoCallback(artistInfoResponse);
            }
        }
    }
}
