package com.zolotuhinartem.lastfminfo.async;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.zolotuhinartem.lastfminfo.LastFmApi.LastFmApi;
import com.zolotuhinartem.lastfminfo.LastFmApi.LastFmApiCaller;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.SearchArtistResponse;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_search.Artists;
import com.zolotuhinartem.lastfminfo.utils.DebugUtils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by Dr on 22-Dec-16.
 */

public class SearchArtistAsyncTaskFragment extends Fragment {
    public static final String TAG = "SearchArtistAsyncTaskFragment";

    private SearchArtistAsyncTask searchArtistAsyncTask;
    private SearchArtistCallback searchArtistCallback;


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
        this.searchArtistCallback = null;
    }

    public void execute(String artistName) {
        if (searchArtistAsyncTask == null){
            searchArtistAsyncTask = new SearchArtistAsyncTask();
            searchArtistAsyncTask.execute(artistName);
        }
    }

    private void attach(Context context) {
        try {
            this.searchArtistCallback = (SearchArtistCallback) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public boolean isWorking(){
        return searchArtistAsyncTask != null;
    }

    public interface SearchArtistCallback {
        void onSearchArtistCallback(SearchArtistResponse searchArtistResponse);
    }

    public class SearchArtistAsyncTask extends AsyncTask<String, Void, SearchArtistResponse> {

        @Override
        protected SearchArtistResponse doInBackground(String... strings) {
            int code = 404;
            Artists artists = null;
            if (strings != null) {
                if (strings[0] != null) {
                    LastFmApiCaller caller = LastFmApi.getLastFmApiCaller();

                    Call call = caller.searchArtist(strings[0], LastFmApi.API_KEY);
                    DebugUtils.i(this, "artist call created");

                    try {
                        DebugUtils.i(this, "start response");
                        Response response = call.execute();
                        code = response.code();
                        artists = (Artists) response.body();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                    }
                }
            }
            DebugUtils.i(this, "end. Code = " + code);
            return new SearchArtistResponse(artists, code);
        }

        @Override
        protected void onPostExecute(SearchArtistResponse searchArtistResponse) {
            searchArtistAsyncTask = null;
            if (searchArtistCallback != null) {
                searchArtistCallback.onSearchArtistCallback(searchArtistResponse);
            }
        }
    }
}
