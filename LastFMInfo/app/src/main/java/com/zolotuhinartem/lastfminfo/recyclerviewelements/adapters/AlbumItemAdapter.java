package com.zolotuhinartem.lastfminfo.recyclerviewelements.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Album;
import com.zolotuhinartem.lastfminfo.R;
import com.zolotuhinartem.lastfminfo.recyclerviewelements.viewholders.AlbumItemViewHolder;

import java.util.List;

/**
 * Created by zolotuhinartem on 18.12.16.
 */

public class AlbumItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Album> list;
    private OnAlbumItemClickListener listener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        return new AlbumItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Album album = list.get(position);
        if (album != null) {
            if (holder instanceof AlbumItemViewHolder) {
                ((AlbumItemViewHolder) holder).bind(album, this.listener);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public void setList(List<Album> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setListener(OnAlbumItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnAlbumItemClickListener{
        void onAlbumItemClick(Album album);
    }
}
