package com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_search.Album;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Image;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.AlbumItemAdapter;

/**
 * Created by zolotuhinartem on 18.12.16.
 */

public class AlbumItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivAlbumCoverHolder;
    private TextView tvAlbumInfo;


    public AlbumItemViewHolder(View itemView) {
        super(itemView);

        ivAlbumCoverHolder = (ImageView) itemView.findViewById(R.id.iv_item_album_cover_holder);
        tvAlbumInfo = (TextView) itemView.findViewById(R.id.tv_item_album_short_info);
    }

    public void bind(final Album album, final AlbumItemAdapter.OnAlbumItemClickListener listener) {
        this.clear();

        if (album != null) {

            String info = album.getArtistName() + " - " + album.getName();
            tvAlbumInfo.setText(info);

            Image image = album.getLargeImage();



            if (image != null) {
                if (image.getUrl() != null) {
                    Glide.with(itemView.getContext())
                            .load(image.getUrl())
                            .fitCenter()
                            .into(ivAlbumCoverHolder);
                }
            }

            if (listener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onAlbumItemClick(album);
                    }
                });
            }

            if (album.getMbid().length() <= 0) {
                this.setTextColor(R.color.colorAccentText);
            }
        }

    }
    public void clear(){
        this.ivAlbumCoverHolder.setImageResource(0);
        this.setTextColor(R.color.colorText);
    }

    public void setTextColor(int id){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tvAlbumInfo.setTextColor(itemView.getContext().getColor(id));
        } else {
            tvAlbumInfo.setTextColor(itemView.getContext().getResources().getColor(id));
        }

    }

}
