package com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Image;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_artist_fromCountry.Artist;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.TopArtistItemAdapter;

/**
 * Created by Dr on 10-Jan-17.
 */

public class TopArtistItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView name;

    public TopArtistItemViewHolder(View itemView) {
        super(itemView);
        this.name = (TextView) itemView.findViewById(R.id.tv_item_top_artist_topartistname);
        this.imageView = (ImageView) itemView.findViewById(R.id.iv_item_artist_topartistphoto);
    }


    public void bind(@NonNull final Artist artist, @NonNull final TopArtistItemAdapter.OnTopArtistItemClickListener listener) {

        name.setText(artist.getName());

        Image image = artist.getLargeImage();


        if (image != null) {
            if (image.getUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(image.getUrl())
                        .fitCenter()
                        .into(imageView);
            }
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTopArtistItemClick(artist);
            }
        });

    }
}
