/*
package com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_info.Image;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.ArtistInfoAdapter;

*/
/**
 * Created by Dr on 29-Dec-16.
 *//*


public class ArtistInfoViewHolder extends RecyclerView.ViewHolder {

    private ImageView photo;

    public ArtistInfoViewHolder(View itemView) {
        super(itemView);
        photo = (ImageView) itemView.findViewById(R.id.iv_item_artist_info);
    }

    public void bind(@NonNull final Image image, @NonNull final ArtistInfoAdapter.ArtistInfoClickListener listener){


        if (image.getUrl() != null) {
            Glide.with(itemView.getContext())
                    .load(image.getUrl())
                    .fitCenter()
                    .into(photo);
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onArtistInfoClick(image);
            }
        });

    }
}
*/
