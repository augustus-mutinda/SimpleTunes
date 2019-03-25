package com.augustus.simpletunes.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.cast.framework.CastSession;
import com.augustus.simpletunes.TunesPlayer;
import com.augustus.simpletunes.activities.BaseActivity;
import com.augustus.simpletunes.cast.TimberCastHelper;
import com.augustus.simpletunes.models.Song;
import com.augustus.simpletunes.utils.NavigationUtils;
import com.augustus.simpletunes.utils.TunesUtils;

import java.util.List;

public class BaseSongAdapter<V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(V holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(View view) {
            super(view);
        }

    }

    public void playAll(final Activity context, final long[] list, int position,
                        final long sourceId, final TunesUtils.IdType sourceType,
                        final boolean forceShuffle, final Song currentSong, boolean navigateNowPlaying) {

        if (context instanceof BaseActivity) {
            CastSession castSession = ((BaseActivity) context).getCastSession();
            if (castSession != null) {
                navigateNowPlaying = false;
                TimberCastHelper.startCasting(castSession, currentSong);
            } else {
                TunesPlayer.playAll(context, list, position, -1, TunesUtils.IdType.NA, false);
            }
        } else {
            TunesPlayer.playAll(context, list, position, -1, TunesUtils.IdType.NA, false);
        }

        if (navigateNowPlaying) {
            NavigationUtils.navigateToNowplaying(context, true);
        }


    }
    public void removeSongAt(int i){}
    public void updateDataSet(List<Song> arraylist) {}

}
