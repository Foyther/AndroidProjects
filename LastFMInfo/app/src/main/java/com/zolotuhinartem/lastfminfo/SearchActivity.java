package com.zolotuhinartem.lastfminfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {


    public static final int SELECTED_RADIO_NONE = -1;
    public static final int SELECTED_RADIO_ALBUM = 1;
    public static final int SELECTED_RADIO_ARTIST_OR_BAND = 2;
    public static final int SELECTED_RADIO_TOP_ARTIST = 3;
    public static final String NAME_FOR_SEARCH = "name_for_find";

    private Button btnCancel, btnSearch;
    private EditText etTarget;
    private View mainView;
    private RadioButton rbAlbum, rbArtistOrBand, rbTopArtists;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnCancel = (Button) findViewById(R.id.btn_activity_search_cancel);
        btnSearch = (Button) findViewById(R.id.btn_activity_search_search);
        rbAlbum = (RadioButton) findViewById(R.id.rb_activity_find_album);
        rbArtistOrBand = (RadioButton) findViewById((R.id.rb_activity_search_artist_or_band));
        rbTopArtists = (RadioButton) findViewById(R.id.rb_activity_search_top_artist);
        radioGroup = (RadioGroup) findViewById(R.id.rg_activity_search);
        etTarget = (EditText) findViewById(R.id.et_activity_search);

        mainView = findViewById(R.id.activity_search);


        btnCancel.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_search_cancel:
                this.finish();
                break;
            case R.id.btn_activity_search_search:
                String nameForSearch;
                nameForSearch = etTarget.getText().toString();
                if (StringManager.deleteSpacesInStartAndEnd(nameForSearch).length() > 0) {
                    int selectedRadio = currentSelectedRadio();
                    switch (selectedRadio) {
                        case SELECTED_RADIO_NONE:
                            Snackbar.make(mainView, R.string.choose_thing_which_you_want_search, Snackbar.LENGTH_LONG).show();
                            break;
                        case SELECTED_RADIO_ALBUM:
                            startActivitySearch(SearchedAlbumsActivity.class, nameForSearch);
                            break;
                        case SELECTED_RADIO_ARTIST_OR_BAND:
                            startActivitySearch(SearchedArtistsOrBandsActivity.class, nameForSearch);
                            break;
                        case SELECTED_RADIO_TOP_ARTIST:
                            startActivitySearch(TopArtistActivity.class, nameForSearch);
                    }
                } else {
                    DebugUtils.i(this, "error");
                    Snackbar.make(mainView, R.string.field_is_empty, Snackbar.LENGTH_LONG).show();
                }
                break;


        }
    }

    public void startActivitySearch(Class c, String nameForSearch) {
        Intent intent = new Intent(this, c);
        intent.putExtra(NAME_FOR_SEARCH, nameForSearch);
        startActivity(intent);
    }

    public int currentSelectedRadio() {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case (R.id.rb_activity_find_album):
                return SELECTED_RADIO_ALBUM;
            case (R.id.rb_activity_search_artist_or_band):
                return SELECTED_RADIO_ARTIST_OR_BAND;
            case (R.id.rb_activity_search_top_artist):
                return SELECTED_RADIO_TOP_ARTIST;
            default:
                return SELECTED_RADIO_NONE;
        }
    }
}
