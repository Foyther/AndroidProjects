package com.zolotuhinartem.lastfminfo.async;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.zolotuhinartem.lastfminfo.Constants;
import com.zolotuhinartem.lastfminfo.DebugUtils;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.LastFmApi;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.LastFmApiCaller;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.SearchAlbumResponse;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Albums;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public class SearchAlbumAsyncTaskFragment extends Fragment {

    private SearchAlbumCallback searchAlbumCallback;
    private SearchAlbumAsyncTask searchAlbumAsyncTask;


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
        this.searchAlbumCallback = null;
    }

    private void attach(Context context) {
        try {
            this.searchAlbumCallback = (SearchAlbumCallback) context;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    public void execute(String albumName) {
        if (searchAlbumAsyncTask == null) {
            searchAlbumAsyncTask = new SearchAlbumAsyncTask();
            searchAlbumAsyncTask.execute(albumName);
        }
    }

    public boolean isWorking(){
        return searchAlbumAsyncTask != null;
    }

    public interface SearchAlbumCallback {
        void onSearch(SearchAlbumResponse searchAlbumResponse);
    }

    public class SearchAlbumAsyncTask extends AsyncTask<String, Void, SearchAlbumResponse> {


        @Override
        protected SearchAlbumResponse doInBackground(String... strings) {
            int code = 404;
            Albums albums = null;
            if (strings != null) {
                if (strings[0] != null) {
                    LastFmApiCaller caller = LastFmApi.getLastFmApiCaller();

                    Call albumsCall =  caller.searchAlbum(strings[0], LastFmApi.API_KEY);
                    DebugUtils.i(this, "albumsCall created");

                    try {
                        DebugUtils.i(this, "start response");
                        Response response = albumsCall.execute();
                        code = response.code();
                        albums = (Albums) response.body();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassCastException e){
                        e.printStackTrace();
                    }
                }
            }
            DebugUtils.i(this, "end. Code = " + code);
            return new SearchAlbumResponse(albums, code);
        }

        @Override
        protected void onPostExecute(SearchAlbumResponse searchAlbumResponse) {
            searchAlbumAsyncTask = null;
            if(searchAlbumCallback != null) {
                searchAlbumCallback.onSearch(searchAlbumResponse);
            }
        }
    }
}
