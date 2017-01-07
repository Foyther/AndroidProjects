package com.zolotuhinartem.lastfminfo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.activities.SearchActivity;
import com.zolotuhinartem.lastfminfo.activities.top_tracks.TopTracksActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnFind;
    private Button btnTopTracks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFind = (Button) findViewById(R.id.btn_activity_main_find);
        btnFind.setOnClickListener(this);

        btnTopTracks = (Button) findViewById(R.id.btn_activity_main_top_tracks);
        btnTopTracks.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_activity_main_find:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_activity_main_top_tracks:
                intent = new Intent(this, TopTracksActivity.class);
                startActivity(intent);
                break;
        }
    }
}
