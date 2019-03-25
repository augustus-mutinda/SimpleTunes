
package com.augustus.simpletunes.listeners;

/**
 * Listens for playback changes to send the the fragments bound to this activity
 */
public interface MusicStateListener {

    void restartLoader();

    void onPlaylistChanged();

    void onMetaChanged();

}
