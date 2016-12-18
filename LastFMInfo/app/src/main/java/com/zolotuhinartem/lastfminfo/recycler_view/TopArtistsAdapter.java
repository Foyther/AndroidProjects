package com.zolotuhinartem.lastfminfo.recycler_view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Artist;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.Image;
import com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo.TopArtists;
import com.zolotuhinartem.lastfminfo.R;

import java.util.ArrayList;

/**
 * Created by Dr on 17-Dec-16.
 */

public class TopArtistsAdapter extends RecyclerView.Adapter<TopArtistsAdapter.RecyclerViewHolder> {
    private ArrayList<TopArtists> artists = new ArrayList<>();
    private OnArtistClickListener listener;

    public void setListener(OnArtistClickListener listener) {
        this.listener = listener;
    }

    public TopArtistsAdapter(ArrayList<TopArtists> artists) {
        this.artists = artists;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_searched_top_artists, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        TopArtists topArtists = artists.get(position);
        if (holder instanceof RecyclerViewHolder) {
            if (artists != null) {
                holder.bind(topArtists, listener);
            }
        }
    }


    @Override
    public int getItemCount() {
        return artists != null ? artists.size() : 0;
    }

    public interface OnArtistClickListener {
        void onArtistClick(TopArtists artist);
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView photo;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.artistOrBandName);
            photo = (ImageView) itemView.findViewById(R.id.artistOrBandPhoto);

        }

        public void bind(@NonNull final TopArtists artist, @NonNull final OnArtistClickListener listener) {
            for (Artist artist1: artist.getArtist()) {
                name.setText(artist1.getName());
            }
            for (Artist artist1: artist.getArtist()) {
                for (Image image: artist1.getImage()) {
                    Glide.with(itemView.getContext())
                            .load(image.getUrl())
                            .fitCenter()
                            .into(photo);
                }
            }

            if (listener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        listener.onArtistClick(artist);
                    }
                });
            }

        }

    }
}
