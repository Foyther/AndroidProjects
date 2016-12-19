package com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.album_info.Track;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders.AlbumInfoTrackItemViewHolder;

import java.util.List;

/**
 * Created by zolotuhinartem on 19.12.16.
 */

public class AlbumInfoTrackItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Track> list;
    private AlbumInfoTrackItemOnClickListener listener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_track, parent, false);
        return new AlbumInfoTrackItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Track track = list.get(position);
        if (track != null) {
            if (holder instanceof AlbumInfoTrackItemViewHolder) {
                ((AlbumInfoTrackItemViewHolder) holder).bind(track, this.listener);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public List<Track> getList() {
        return list;
    }

    public void setList(List<Track> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public AlbumInfoTrackItemOnClickListener getListener() {
        return listener;
    }

    public void setListener(AlbumInfoTrackItemOnClickListener listener) {
        this.listener = listener;
    }

    public interface AlbumInfoTrackItemOnClickListener {
        void onAlbumInfoTrackItemClick(Track track);
    }
}
