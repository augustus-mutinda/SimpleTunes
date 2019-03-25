package com.augustus.simpletunes.nowplaying;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.augustus.simpletunes.TunesPlayer;
import com.augustus.simpletunes.TunesService;
import com.augustus.simpletunes.R;
import com.augustus.simpletunes.dataloaders.SongLoader;
import com.augustus.simpletunes.models.Song;
import com.augustus.simpletunes.utils.TunesUtils;
import com.augustus.simpletunes.widgets.CircleImageView;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

public class Timber6 extends BaseNowplayingFragment {

    TextView nextSong;
    CircleImageView nextArt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_timber6, container, false);

        setMusicStateListener();
        setSongDetails(rootView);

        initGestures(rootView.findViewById(R.id.album_art));

        ((SeekBar) rootView.findViewById(R.id.song_progress)).getProgressDrawable().setColorFilter(new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY));
        ((SeekBar) rootView.findViewById(R.id.song_progress)).getThumb().setColorFilter(new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP));

        nextSong = (TextView) rootView.findViewById(R.id.title_next);
        nextArt = (CircleImageView) rootView.findViewById(R.id.album_art_next);

        rootView.findViewById(R.id.nextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TunesPlayer.next();
            }
        });

        return rootView;
    }

    @Override
    public void updateShuffleState() {
        if (shuffle != null && getActivity() != null) {
            MaterialDrawableBuilder builder = MaterialDrawableBuilder.with(getActivity())
                    .setIcon(MaterialDrawableBuilder.IconValue.SHUFFLE)
                    .setSizeDp(30);

            if (TunesPlayer.getShuffleMode() == 0) {
                builder.setColor(Color.WHITE);
            } else builder.setColor(accentColor);

            shuffle.setImageDrawable(builder.build());
            shuffle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TunesPlayer.cycleShuffle();
                    updateShuffleState();
                    updateRepeatState();
                }
            });
        }
    }

    @Override
    public void updateRepeatState() {
        if (repeat != null && getActivity() != null) {
            MaterialDrawableBuilder builder = MaterialDrawableBuilder.with(getActivity())
                    .setSizeDp(30);

            if (TunesPlayer.getRepeatMode() == 0) {
                builder.setColor(Color.WHITE);
            } else builder.setColor(accentColor);

            if (TunesPlayer.getRepeatMode() == TunesService.REPEAT_NONE) {
                builder.setIcon(MaterialDrawableBuilder.IconValue.REPEAT);
                builder.setColor(Color.WHITE);
            } else if (TunesPlayer.getRepeatMode() == TunesService.REPEAT_CURRENT) {
                builder.setIcon(MaterialDrawableBuilder.IconValue.REPEAT_ONCE);
                builder.setColor(accentColor);
            } else if (TunesPlayer.getRepeatMode() == TunesService.REPEAT_ALL) {
                builder.setColor(accentColor);
                builder.setIcon(MaterialDrawableBuilder.IconValue.REPEAT);
            }

            repeat.setImageDrawable(builder.build());
            repeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TunesPlayer.cycleRepeat();
                    updateRepeatState();
                    updateShuffleState();
                }
            });
        }
    }

    @Override
    public void onMetaChanged() {
        super.onMetaChanged();
        if (getActivity() != null) {
            long nextId = TunesPlayer.getNextAudioId();
            Song next = SongLoader.getSongForID(getActivity(), nextId);
            nextSong.setText(next.title);
            nextArt.setImageURI(TunesUtils.getAlbumArtUri(next.albumId));
        }
    }
}
