package com.zolotuhinartem.lastfminfo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.activities.SearchActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnFind;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFind = (Button) findViewById(R.id.btn_activity_main_find);
        btnFind.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_activity_main_find:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
        }
    }
}
