package com.zolotuhinartem.lastfminfo.activities.searched_albums;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.SearchAlbumResponse;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_search.Album;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.activities.SearchActivity;
import com.zolotuhinartem.lastfminfo.activities.album_info.AlbumInfoActivity;
import com.zolotuhinartem.lastfminfo.async.SearchAlbumAsyncTaskFragment;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.AlbumItemAdapter;
import com.zolotuhinartem.lastfminfo.utils.StringManager;

public class SearchedAlbumsActivity extends AppCompatActivity implements SearchAlbumAsyncTaskFragment.SearchAlbumCallback, AlbumItemAdapter.OnAlbumItemClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button btnSearch;
    private EditText etSearch;
    private View vRoot;
    private SearchAlbumAsyncTaskFragment searchAlbumAsyncTaskFragment;

    private AlbumItemAdapter albumItemAdapter;

    public static final int COLUMNS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_albums);

        recyclerView = (RecyclerView) findViewById(R.id.rv_activity_searched_albums);
        progressBar = (ProgressBar) findViewById(R.id.pb_activity_searched_albums);
        etSearch = (EditText) findViewById(R.id.et_activity_searched_albums);
        btnSearch = (Button) findViewById(R.id.btn_activity_searched_albums_search);
        vRoot = (View) findViewById(R.id.activity_searched_albums);


        albumItemAdapter = new AlbumItemAdapter();
        albumItemAdapter.setListener(this);
        recyclerView.setAdapter(albumItemAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, COLUMNS, GridLayoutManager.VERTICAL, false));

        searchAlbumAsyncTaskFragment = getSearchAlbumTaskFragment();

        setProgress(searchAlbumAsyncTaskFragment.isWorking());

        if (savedInstanceState == null) {
            String searchName = getIntent().getStringExtra(SearchActivity.NAME_FOR_SEARCH);
            etSearch.setText(searchName);
            search(searchName);
        } else {
            SearchedAlbumsHolderFragment searchedAlbumsHolderFragment = getSeatchetAlbumsHolderFragment();
            albumItemAdapter.setList(searchedAlbumsHolderFragment.getAlbums());
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchName = StringManager.deleteSpacesInStartAndEnd(etSearch.getText().toString());
                if (searchName.length() > 0) {
                    search(searchName);
                }
            }
        });

    }

    private void search(String searchName) {
        searchAlbumAsyncTaskFragment.execute(searchName);
        setProgress(searchAlbumAsyncTaskFragment.isWorking());
    }

    private SearchAlbumAsyncTaskFragment getSearchAlbumTaskFragment() {
        SearchAlbumAsyncTaskFragment fragment = (SearchAlbumAsyncTaskFragment) getFragmentManager().findFragmentByTag(SearchAlbumAsyncTaskFragment.class.getName());

        if (fragment == null) {
            fragment = new SearchAlbumAsyncTaskFragment();
            getFragmentManager().beginTransaction().add(fragment, SearchAlbumAsyncTaskFragment.class.getName()).commit();
        }

        return fragment;
    }

    private SearchedAlbumsHolderFragment getSeatchetAlbumsHolderFragment() {
        SearchedAlbumsHolderFragment fragment = (SearchedAlbumsHolderFragment) getFragmentManager().findFragmentByTag(SearchedAlbumsHolderFragment.class.getName());
        if (fragment == null) {
            fragment = new SearchedAlbumsHolderFragment();
            getFragmentManager().beginTransaction().add(fragment, SearchedAlbumsHolderFragment.class.getName()).commit();
        }

        return fragment;
    }

    public void setProgress(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSearchAlbumCallback(SearchAlbumResponse searchAlbumResponse) {
        boolean isError;
        isError = false;

        if (searchAlbumResponse.getCode() >= 400) {
            isError = true;
        } else {
            if (searchAlbumResponse.getAlbums() == null) {
                isError = true;
            }
        }
        if (!isError) {
            albumItemAdapter.setList(searchAlbumResponse.getAlbums().getResults().getAlbumMatches().getAlbum());
        } else {
            showError(R.string.search_error);
        }
        setProgress(false);

    }

    public void showError(String text){
        showSnack(text);
    }

    public void showError(int id){
        showSnack(id);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSeatchetAlbumsHolderFragment().setAlbums(albumItemAdapter.getList());
        super.onSaveInstanceState(outState);

    }

    public  void showSnack(String text) {
        Snackbar.make(vRoot, text, Snackbar.LENGTH_LONG).show();
    }

    public  void showSnack(int id) {
        Snackbar.make(vRoot, id, Snackbar.LENGTH_LONG).show();
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void showToast(int id) {
        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAlbumItemClick(Album album) {
        if (album != null) {
            if (album.getMbid().length() > 0) {
                Intent intent = new Intent(this, AlbumInfoActivity.class);
                intent.putExtra(AlbumInfoActivity.ALBUM_ID, album.getMbid());
                startActivity(intent);
            } else {
                showToast(R.string.this_does_not_have_a_web_page_please_try_select_another);
            }
        }
    }
}
