package com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_search.Artist;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders.ArtistItemViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by Dr on 22-Dec-16.
 */

public class ArtistItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Artist> list;
    private OnArtistItemClickListener listener;

    public void setListener(OnArtistItemClickListener listener) {
        this.listener = listener;
    }

    public void setList(List<Artist> list) {
        this.list = list;
        Collections.sort(this.list);

        notifyDataSetChanged();
    }

    public List<Artist> getList() {
        return list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_artist, parent, false);
        return new ArtistItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Artist artist = list.get(position);
        if (artist != null) {
            if (holder instanceof ArtistItemViewHolder) {
                ((ArtistItemViewHolder) holder).bind(artist, listener);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public interface OnArtistItemClickListener {
        void onArtistItemClick(Artist artist);
    }
}
