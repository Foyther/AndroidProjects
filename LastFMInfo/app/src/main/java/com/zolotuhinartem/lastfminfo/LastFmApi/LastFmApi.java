package com.zolotuhinartem.lastfminfo.LastFmApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public class LastFmApi {

    public static final String API_KEY = "dae48634fc7b97f4f4827bd48278877d";
    public static final String BASE_URL = "http://ws.audioscrobbler.com";


    private static LastFmApiCaller lastFmApiCaller;

    public static LastFmApiCaller getLastFmApiCaller(){

        if (lastFmApiCaller == null) {
            lastFmApiCaller = (new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()).create(LastFmApiCaller.class);
        }

        return lastFmApiCaller;
    }


}
