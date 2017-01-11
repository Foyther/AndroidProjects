package com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_search.Album;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.top_tracks.Track;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders.AlbumItemViewHolder;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders.TopTracksHolder;

import java.util.List;

/**
 * Created by Nurislam on 02.01.2017.
 */

public class TopTracksAdapter extends RecyclerView.Adapter<TopTracksHolder> {
    private List<Track> topTracks;
    private TextView tvTrack;
    private int index;
    private OnTrackClickListenner onTrackClickListenner;

    public TopTracksAdapter(List<Track> topTracks) {
        this.topTracks = topTracks;
    }

    public TopTracksAdapter(OnTrackClickListenner onTrackClickListenner) {
        this.onTrackClickListenner = onTrackClickListenner;
    }

    public List<Track> getTopTracks() {
        return topTracks;
    }

    public TextView getTvTrack() {
        return tvTrack;
    }

    public void setTvTrack(TextView tvTrack) {
        this.tvTrack = tvTrack;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public OnTrackClickListenner getOnTrackClickListenner() {
        return onTrackClickListenner;
    }

    public void setOnTrackClickListenner(OnTrackClickListenner onTrackClickListenner) {
        this.onTrackClickListenner = onTrackClickListenner;
    }

    @Override
    public void onBindViewHolder(TopTracksHolder holder, int position) {
        Track track = topTracks.get(position);
        if (track != null) {

            holder.bind(position+1, track, onTrackClickListenner);

        }
    }

    public void setTopTracks(List<Track> topTracks) {
        this.topTracks = topTracks;
    }

    @Override
    public int getItemCount() {
        if (topTracks != null) {
            return topTracks.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public TopTracksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_track_view, parent, false);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.second_track_view, parent, false);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_track_view, parent, false);
                break;
        }
        return new TopTracksHolder(view);
    }


    public interface OnTrackClickListenner {
        void onTrackItemClick(Track track);
    }
}
