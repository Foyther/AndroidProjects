package com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_tracks.Track;
import com.zolotuhinartem.lastfminfo.R;

import java.util.List;

/**
 * Created by Nurislam on 02.01.2017.
 */

public class TopTracksAdapter extends RecyclerView.Adapter<TopTracksAdapter.TopTracksHolder> {
    private List<Track> topTracks;
    private TextView tvTrack;
    private int index;

    public TopTracksAdapter(List<Track> topTracks) {
        this.topTracks = topTracks;
    }

    @Override
    public void onBindViewHolder(TopTracksHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return topTracks.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public TopTracksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0: view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_track_view, parent, false);
                break;
            case 1: view = LayoutInflater.from(parent.getContext()).inflate(R.layout.second_track_view, parent, false);
                break;
            default: view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_track_view, parent, false);
                break;
        }
        return new TopTracksHolder(view);
    }


    public class TopTracksHolder extends RecyclerView.ViewHolder {

        public TopTracksHolder(View itemView) {
            super(itemView);
            tvTrack = (TextView) itemView.findViewById(R.id.track_name);
            index++;
            tvTrack.setText(index +". "+ topTracks.get(index-1).getName());
        }
    }
}
