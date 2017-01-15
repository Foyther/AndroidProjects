package com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.artist_info.Image;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders.ArtistInfoViewHolder;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders.ArtistItemViewHolder;

import java.util.List;

/**
 * Created by Dr on 29-Dec-16.
 */

public class ArtistInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Image> list;
    private ArtistInfoClickListener listener;

    public List<Image> getList() {
        return list;
    }

    public void setList(List<Image> list) {
        this.list = list;
    }

    public void setListener(ArtistInfoClickListener listener) {
        this.listener = listener;
    }

    public interface ArtistInfoClickListener {
        void onArtistInfoClick(Image image);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist_info_photo, parent, false);
        return new ArtistItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Image image = list.get(position);
        if (image != null) {
            if (holder instanceof ArtistInfoViewHolder) {
                ((ArtistInfoViewHolder) holder).bind(image, listener);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : null;
    }
}
