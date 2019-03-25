
package com.augustus.simpletunes.lastfmapi.callbacks;

import com.augustus.simpletunes.lastfmapi.models.LastfmAlbum;

public interface AlbumInfoListener {

    void albumInfoSuccess(LastfmAlbum album);

    void albumInfoFailed();

}
