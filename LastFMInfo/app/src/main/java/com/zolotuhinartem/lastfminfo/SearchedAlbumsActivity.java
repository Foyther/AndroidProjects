package com.zolotuhinartem.lastfminfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.SearchAlbumResponse;
import com.zolotuhinartem.lastfminfo.async.SearchAlbumAsyncTaskFragment;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.AlbumItemAdapter;

public class SearchedAlbumsActivity extends AppCompatActivity implements SearchAlbumAsyncTaskFragment.SearchAlbumCallback {

    private RecyclerView recyclerView;
    private AlbumItemAdapter albumItemAdapter;
    public static final int COLUMNS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_albums);

        recyclerView = (RecyclerView) findViewById(R.id.rv_activity_searched_albums);
        albumItemAdapter = new AlbumItemAdapter();
        recyclerView.setAdapter(albumItemAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, COLUMNS, GridLayoutManager.VERTICAL, false));

        SearchAlbumAsyncTaskFragment fragment = (SearchAlbumAsyncTaskFragment) getFragmentManager().findFragmentByTag(SearchAlbumAsyncTaskFragment.class.getName());

        if (fragment == null) {
            fragment = new SearchAlbumAsyncTaskFragment();
            getFragmentManager().beginTransaction().add(fragment, SearchAlbumAsyncTaskFragment.class.getName()).commit();
        }


        fragment.execute(getIntent().getStringExtra(SearchActivity.NAME_FOR_SEARCH));
    }

    @Override
    public void onSearch(SearchAlbumResponse searchAlbumResponse) {
        if (searchAlbumResponse.getCode() < 400){
            if (searchAlbumResponse.getAlbums() != null){
                albumItemAdapter.setList(searchAlbumResponse.getAlbums().getResults().getAlbumMatches().getAlbum());
            }
        }
//        showToast(searchAlbumResponse.getCode() + " " + searchAlbumResponse.getAlbums().getResults().getOpensearchTotalResults());
    }

    public void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
