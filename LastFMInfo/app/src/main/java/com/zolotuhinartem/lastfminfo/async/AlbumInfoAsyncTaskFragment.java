package com.zolotuhinartem.lastfminfo.async;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.zolotuhinartem.lastfminfo.LastFmApi.LastFmApi;
import com.zolotuhinartem.lastfminfo.LastFmApi.LastFmApiCaller;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.AlbumInfoResponse;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info.Album;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info.AlbumContainer;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zolotuhinartem on 19.12.16.
 */

public class AlbumInfoAsyncTaskFragment extends Fragment {



    private AlbumInfoAsyncTask albumInfoAsyncTask;
    private AlbumInfoCallback albumInfoCallback;

    public void execute(String mbid){
        if (this.albumInfoAsyncTask == null){
            this.albumInfoAsyncTask = new AlbumInfoAsyncTask();
            this.albumInfoAsyncTask.execute(mbid);
        }
    }

    public boolean isWorking(){
        return this.albumInfoAsyncTask != null;
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
        this.albumInfoCallback = null;
    }

    private void attach(Context context) {
        try {
            this.albumInfoCallback = (AlbumInfoCallback) context;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    public interface AlbumInfoCallback{
        void onAlbumInfoCallback(AlbumInfoResponse albumInfoResponse);
    }

    public class AlbumInfoAsyncTask extends AsyncTask<String, Void, AlbumInfoResponse>{

        @Override
        protected AlbumInfoResponse doInBackground(String... params) {
            AlbumInfoResponse albumInfoResponse = null;
            if (params != null) {
                if (params[0] != null) {
                    LastFmApiCaller caller = LastFmApi.getLastFmApiCaller();
                    Call<AlbumContainer> albumCall = caller.getAlbumInfo(params[0], LastFmApi.API_KEY);
                    Album album= null;
                    int code = 1000;
                    try {
                        Response<AlbumContainer> albumResponse = albumCall.execute();
                        code = albumResponse.code();
                        AlbumContainer albumContainer = albumResponse.body();
                        album = albumContainer.getAlbum();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassCastException ex) {
                        ex.printStackTrace();
                    } catch (NullPointerException ex) {
                        ex.printStackTrace();
                    }
                    albumInfoResponse = new AlbumInfoResponse(album, code);
                }
            }
            return albumInfoResponse;
        }

        @Override
        protected void onPostExecute(AlbumInfoResponse albumInfoResponse) {
            albumInfoAsyncTask = null;
            if (albumInfoCallback != null) {
                albumInfoCallback.onAlbumInfoCallback(albumInfoResponse);
            }
        }
    }




}
