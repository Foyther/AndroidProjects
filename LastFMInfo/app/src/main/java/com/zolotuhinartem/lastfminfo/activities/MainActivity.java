package com.zolotuhinartem.lastfminfo.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.activities.searched_albums.SearchedAlbumsActivity;
import com.zolotuhinartem.lastfminfo.activities.searched_artists.SearchedArtistsActivity;
import com.zolotuhinartem.lastfminfo.activities.top_tracks.TopTracksActivity;
import com.zolotuhinartem.lastfminfo.utils.DebugUtils;
import com.zolotuhinartem.lastfminfo.utils.StringManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String SEARCH_FOCUS = "search_focus";

    public static final int SELECTED_RADIO_NONE = -1;
    public static final int SELECTED_RADIO_ALBUM = 1;
    public static final int SELECTED_RADIO_ARTIST = 2;

    private Button btnSearch;
    private Button btnTopTracks;

    private Button btnCancel, btnSearch2;
    private EditText etTarget;
    private View mainView;
    private RadioGroup radioGroup;

    private View searchBox, btnBox;

    private boolean searchFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        searchBox = (View) findViewById(R.id.rl_activity_main_search_box);
        btnBox = (View) findViewById(R.id.ll_activity_main);
        btnSearch = (Button) findViewById(R.id.btn_activity_main_search);


        btnTopTracks = (Button) findViewById(R.id.btn_activity_main_top_tracks);



        btnCancel = (Button) findViewById(R.id.btn_activity_main_cancel);
        btnSearch2 = (Button) findViewById(R.id.btn_activity_main_search2);
        radioGroup = (RadioGroup) findViewById(R.id.rg_activity_main);
        etTarget = (EditText) findViewById(R.id.et_activity_main);

        mainView = findViewById(R.id.activity_main);

        btnTopTracks.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSearch2.setOnClickListener(this);

        searchFocus = false;
        if (savedInstanceState != null) {
            searchFocus = savedInstanceState.getBoolean(SEARCH_FOCUS);
        }
        searchFocus(searchFocus);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(SEARCH_FOCUS, this.searchFocus);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_activity_main_search:
                searchFocus(true);
                break;
            case R.id.btn_activity_main_top_tracks:
                intent = new Intent(this, TopTracksActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_activity_main_cancel:
                searchFocus(false);
                break;
            case R.id.btn_activity_main_search2:
                String nameForSearch;
                nameForSearch = etTarget.getText().toString();
                if (StringManager.deleteSpacesInStartAndEnd(nameForSearch).length() > 0) {
                    int selectedRadio = currentSelectedRadio();
                    switch (selectedRadio) {
                        case SELECTED_RADIO_NONE:
                            Snackbar.make(mainView, R.string.choose_thing_which_you_want_search, Snackbar.LENGTH_LONG).show();
                            break;
                        case SELECTED_RADIO_ALBUM:
                            startActivitySearch(SearchedAlbumsActivity.class, SearchedAlbumsActivity.ALBUM_NAME, nameForSearch);
                            break;
                        case SELECTED_RADIO_ARTIST:
                            startActivitySearch(SearchedArtistsActivity.class, SearchedArtistsActivity.ARTIST_NAME, nameForSearch);
                            break;
                    }
                } else {
                    DebugUtils.i(this, "error");
                    Snackbar.make(mainView, R.string.field_is_empty, Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void searchFocus(boolean isSearch) {
        searchFocus = isSearch;
        if (isSearch) {
            btnSearch.setVisibility(View.GONE);
            btnBox.setVisibility(View.VISIBLE);
            searchBox.setVisibility(View.VISIBLE);
        } else {
            btnSearch.setVisibility(View.VISIBLE);
            btnBox.setVisibility(View.GONE);
            searchBox.setVisibility(View.GONE);
        }

    }

    public void startActivitySearch(Class c, String key, String nameForSearch) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, nameForSearch);
        startActivity(intent);
    }

    public int currentSelectedRadio() {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case (R.id.rb_activity_main_album):
                return SELECTED_RADIO_ALBUM;
            case (R.id.rb_activity_main_artist_or_band):
                return SELECTED_RADIO_ARTIST;
            default:
                return SELECTED_RADIO_NONE;
        }
    }
}
