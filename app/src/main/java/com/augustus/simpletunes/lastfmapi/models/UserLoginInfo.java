package com.augustus.simpletunes.lastfmapi.models;

import com.google.gson.annotations.SerializedName;
import com.augustus.simpletunes.lastfmapi.LastFmClient;

public class UserLoginInfo {
    private static final String SESSION = "session";

    @SerializedName(SESSION)
    public LastfmUserSession mSession;


}
