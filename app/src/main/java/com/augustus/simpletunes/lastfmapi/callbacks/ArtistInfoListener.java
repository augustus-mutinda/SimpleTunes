
package com.augustus.simpletunes.lastfmapi.callbacks;

import com.augustus.simpletunes.lastfmapi.models.LastfmArtist;

public interface ArtistInfoListener {

    void artistInfoSucess(LastfmArtist artist);

    void artistInfoFailed();

}
