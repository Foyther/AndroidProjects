package com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Image;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_search.Artist;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.ArtistItemAdapter;

/**
 * Created by Dr on 22-Dec-16.
 */

public class ArtistItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView name;

    public ArtistItemViewHolder(View itemView) {
        super(itemView);
        this.name = (TextView) itemView.findViewById(R.id.tv_item_top_artist_topartistname);
        this.imageView = (ImageView) itemView.findViewById(R.id.iv_item_artist_topartistphoto);
    }



    public void bind(@NonNull final Artist artist, @NonNull final ArtistItemAdapter.OnArtistItemClickListener listener) {

        clear();

        name.setText(artist.getName());

        if (artist.getMbid().length() <= 0) {
            this.setTextColor(R.color.colorAccentText);
        }
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
                listener.onArtistItemClick(artist);
            }
        });

    }

    public void clear(){
        this.imageView.setImageResource(0);
        this.setTextColor(R.color.colorText);
    }

    public void setTextColor(int id){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            name.setTextColor(itemView.getContext().getColor(id));
        } else {
            name.setTextColor(itemView.getContext().getResources().getColor(id));
        }

    }

}
