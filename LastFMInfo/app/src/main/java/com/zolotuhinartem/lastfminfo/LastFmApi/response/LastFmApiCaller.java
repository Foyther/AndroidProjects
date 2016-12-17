package com.zolotuhinartem.lastfminfo.LastFmApi.response;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Albums;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public interface LastFmApiCaller {


    @GET("2.0/?method=album.search&format=json")
    Call<Albums> searchAlbum(@Query("album") String album, @Query("api_key") String apiKey);
}
