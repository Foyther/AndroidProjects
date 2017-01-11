package com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_tracks.Track;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters.TopTracksAdapter;

/**
 * Created by Nurislam on 11.01.2017.
 */

public class TopTracksHolder extends RecyclerView.ViewHolder {
    private TextView tvTrack;

    public TopTracksHolder(View itemView) {
        super(itemView);
        tvTrack = (TextView) itemView.findViewById(R.id.track_name);
    }

    public void bind(int index, final Track track, final TopTracksAdapter.OnTrackClickListenner listener) {
        tvTrack.setText(index +". "+ track.getArtist().getName() + " - " + track.getName());

        if (listener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTrackItemClick(track);
                }
            });
        }
    }
}
