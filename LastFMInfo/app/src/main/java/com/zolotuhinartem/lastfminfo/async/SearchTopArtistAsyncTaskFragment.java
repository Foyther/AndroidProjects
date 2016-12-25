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
 * Created by Dr on 25-Dec-16.
 */

public class SearchTopArtistAsyncTaskFragment extends Fragment {

    private SearchTopArtistAsyncTask searchTopArtistAsyncTask;
    private SearchTopArtistCallback searchTopArtistCallback;


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
        this.searchTopArtistCallback = null;
    }

    public void execute(String countryName) {
        if (searchTopArtistAsyncTask == null) {
            searchTopArtistAsyncTask = new SearchTopArtistAsyncTask();
            searchTopArtistAsyncTask.execute(countryName);
        }
    }

    private void attach(Context context) {
        try {
            this.searchTopArtistCallback = (SearchTopArtistCallback) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public boolean isWorking() {
        return searchTopArtistAsyncTask != null;
    }

    public interface SearchTopArtistCallback {
        void onSearchTopArtistCallback(SearchArtistResponse searchArtistResponse);
    }

    public class SearchTopArtistAsyncTask extends AsyncTask<String, Void, SearchArtistResponse> {

        @Override
        protected SearchArtistResponse doInBackground(String... strings) {
            int code = 404;
            Artists artists = null;
            if (strings != null) {
                if (strings[0] != null) {
                    LastFmApiCaller caller = LastFmApi.getLastFmApiCaller();

                    Call call = caller.searchTopArtists(strings[0], LastFmApi.API_KEY);
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
            searchTopArtistAsyncTask = null;
            if (searchTopArtistCallback != null) {
                searchTopArtistCallback.onSearchTopArtistCallback(searchArtistResponse);
            }
        }
    }
}
