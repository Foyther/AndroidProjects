package com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info.Track;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.AlbumInfoTrackItemAdapter;

/**
 * Created by zolotuhinartem on 19.12.16.
 */

public class AlbumInfoTrackItemViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName;

    public AlbumInfoTrackItemViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_item_track_name);
    }

    public void bind(final Track track, final AlbumInfoTrackItemAdapter.AlbumInfoTrackItemOnClickListener listener) {
        if (track != null) {
            this.tvName.setText(track.getAttr().getRank() + " - " + track.getName());

            if (listener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onAlbumInfoTrackItemClick(track);
                    }
                });
            }
        }
    }
}
