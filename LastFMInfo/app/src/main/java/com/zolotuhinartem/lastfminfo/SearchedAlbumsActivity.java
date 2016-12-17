package com.zolotuhinartem.lastfminfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.SearchAlbumResponse;
import com.zolotuhinartem.lastfminfo.async.SearchAlbumAsyncTaskFragment;

public class SearchedAlbumsActivity extends AppCompatActivity implements SearchAlbumAsyncTaskFragment.SearchAlbumCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_albums);

        SearchAlbumAsyncTaskFragment fragment = (SearchAlbumAsyncTaskFragment) getFragmentManager().findFragmentByTag(SearchAlbumAsyncTaskFragment.class.getName());

        if (fragment == null) {
            fragment = new SearchAlbumAsyncTaskFragment();
            getFragmentManager().beginTransaction().add(fragment, SearchAlbumAsyncTaskFragment.class.getName()).commit();
        }


        fragment.execute(getIntent().getStringExtra(SearchActivity.NAME_FOR_SEARCH));
    }

    @Override
    public void onSearch(SearchAlbumResponse searchAlbumResponse) {
        showToast(searchAlbumResponse.getCode() + " " + searchAlbumResponse.getAlbums().getResults().getOpensearchTotalResults());
    }

    public void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
