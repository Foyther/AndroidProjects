package com.zolotuhinartem.lastfminfo.LastFmApi;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info.AlbumContainer;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_search.Albums;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_search.Artists;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public interface LastFmApiCaller {


    @GET("2.0/?method=album.search&format=json")
    Call<Albums> searchAlbum(@Query("album") String album, @Query("api_key") String apiKey);

    @GET("2.0/?method=geo.getTopArtists&format=json")
    Call<Artists> searchTopArtists(@Query("country") String artist, @Query("api_key") String apiKey);

    @GET("2.0/?method=artist.search&format=json")
    Call<Artists> searchArtist(@Query("artist") String artist, @Query("api_key") String apiKey);

    @GET("2.0/?method=artist.getinfo&format=json")
    Call<com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_info.Artists> getArtistInfo(@Query("artist") String artistName, @Query("api_key") String apiKey);


    @GET("2.0/?method=album.getinfo&format=json")
    Call<AlbumContainer> getAlbumInfo(@Query("mbid") String mbid, @Query("api_key") String apiKey);

    @GET("2.0/?method=album.getinfo&format=json")
    Call<AlbumContainer> getAlbumInfo(@Query("artist") String artistName, @Query("album") String albumName, @Query("api_key") String apiKey);
//    artist=Cher&album=Believe
//    http://ws.audioscrobbler.com/&api_key=dae48634fc7b97f4f4827bd48278877d&mbid=61bf0388-b8a9-48f4-81d1-7eb02706dfb0


}
